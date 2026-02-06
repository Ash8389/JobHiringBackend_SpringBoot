package com.example.demo.dto.utils;

import com.example.demo.dto.UserResponse;
import com.example.demo.model.User;

public class UserToUserResponseMapper {
    public UserResponse mapper(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setPhoneno(user.getPhoneno());
        userResponse.setEmail(user.getEmail());

        return userResponse;
    }
}
