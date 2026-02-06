package com.example.demo.service;

import com.example.demo.dto.UserResponse;
import com.example.demo.dto.utils.UserToUserResponseMapper;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.model.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse addUser(User user){
        user.setPassword( passwordEncoder.encode(user.getPassword()) );
        User saved = userRepository.save(user);
        UserToUserResponseMapper mapper = new UserToUserResponseMapper();
        return  mapper.mapper(saved);
    }

    public String removeUser(String id) {

        if(!userRepository.existsById(id)){
            throw new UsernameNotFoundException("User with id : " + id + " not exist");
        }

        userRepository.deleteById(id);

        return "User with id : " + id + "deleted";
    }

    public UserResponse updateUser(User details){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !authentication.isAuthenticated()){
            throw new RuntimeException("User not authenticated");
        }
        CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();
        User user =  userRepository.findById(userDetail.getUserId()).orElseThrow(
                () -> new RuntimeException("User does not exist")
        );
        if(details.getUsername() != null && !details.getUsername().isBlank()){
            user.setUsername(details.getUsername());
        }
        if(details.getPhoneno() != null && !details.getPhoneno().isBlank()){
            user.setPhoneno(details.getPhoneno());
        }
        if(details.getEmail() != null && !details.getEmail().isBlank()){
            user.setEmail(details.getEmail());
        }


        userRepository.save(user);
        UserToUserResponseMapper mapper = new UserToUserResponseMapper();
        return  mapper.mapper(user);

    }

    public UserResponse getUserDetails(String id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        );

        UserToUserResponseMapper mapper = new UserToUserResponseMapper();
        return  mapper.mapper(user);
    }
}
