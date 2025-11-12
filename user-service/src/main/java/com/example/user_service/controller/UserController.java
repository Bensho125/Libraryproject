package com.example.user_service.controller;

import com.example.user_service.model.User;
import com.example.user_service.service.JwtService;
import com.example.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    @GetMapping("/getById/{id}")
    public User getById(@PathVariable int id){
        return userService.getById(id);
    }

    @PostMapping("/register")
    ResponseEntity<User> register(@RequestBody User user){
        return  userService.saveUser(user);

    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }
        else{
            return "login failed";
        }
    }
}
