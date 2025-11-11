package com.example.user_service.feign;


import com.example.user_service.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface UserInterface {

    @PostMapping("auth/register")
    ResponseEntity<User> register(@RequestBody User user);

    @PostMapping("auth/login")
    public String login(@RequestBody User user);
}
