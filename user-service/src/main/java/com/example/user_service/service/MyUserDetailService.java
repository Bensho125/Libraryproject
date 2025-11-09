package com.example.user_service.service;

import com.example.user_service.model.User;
import com.example.user_service.model.UserPrincipal;
import com.example.user_service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo repo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repo.findByUsername(username);

        if( user == null){
            System.out.println("user not found: " + username);
            throw new UsernameNotFoundException("user 404");
        }

        System.out.println("user found: " + user);

        UserPrincipal principal = new UserPrincipal(user);

        return principal;
    }
}
