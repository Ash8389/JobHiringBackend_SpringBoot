package com.example.demo.controller;

import com.example.demo.dto.UserResponse;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserResponse getUserDetails(@PathVariable("id") String id){
        return userService.getUserDetails(id);
    }
    @PostMapping("/register")
    public UserResponse addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") String id) {
        return userService.removeUser(id);
    }
    @PutMapping("/update")
    public UserResponse updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
}