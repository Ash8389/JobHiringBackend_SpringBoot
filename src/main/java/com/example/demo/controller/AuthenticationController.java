package com.example.demo.controller;

import com.example.demo.dto.UserRequest;
import com.example.demo.jwt.utils.JwtUtils;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AuthService logoutService;
    @PostMapping("/login")
    public String login(@RequestBody UserRequest user){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        if(authentication.isAuthenticated()){
            return jwtUtils.tokenGenerator(user);
        }else{
            return "Invalid User";
        }
    }
    @PostMapping("/logout")
    public String logout(@RequestHeader(name = "Authorization", required = false) String header) {

        String token = header.substring(7);
        return logoutService.logout(token);
    }
}