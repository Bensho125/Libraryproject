package com.example.user_service.service;

import com.example.user_service.model.User;
import com.example.user_service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    String[] roles = {"CUSTOMER", "LIBRARIAN"};

    @Autowired
    private UserRepo repo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public ResponseEntity saveUser(User user){
        boolean success = false;
        for (String role: roles){
            if(Objects.equals(user.getRole(), role)){
                success = true;
            }
        }

        if(!success){
            return new ResponseEntity<>("invalid role", HttpStatus.UNAUTHORIZED);
        }

        user.setPassword(encoder.encode(user.getPassword()));
//        user.setId(repo.findAll().size() + 1);
        repo.save(user);
        return new ResponseEntity(user, HttpStatus.OK);
    }
}
