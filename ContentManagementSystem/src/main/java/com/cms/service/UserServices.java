package com.cms.service;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cms.entity.AppUser;
import com.cms.repository.UserRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class UserServices implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Fetch user by email, assuming findByEmail returns Optional<AppUser>
        AppUser user = repository.findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Return UserDetails with authorities (if any)
        return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getPassword(),
            new ArrayList<>() // Add authorities if needed
        );
    }


    public String saveUser(AppUser appUser) {
        // Check if the email already exists
        boolean emailExists = repository.findByEmail(appUser.getEmail()).isPresent();
        
        if (emailExists) {
            return "Email address is already used";
        } else {
            // Encode the password
            BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();
            appUser.setPassword(bcryptEncoder.encode(appUser.getPassword()));
            
            // Save the new user
            repository.save(appUser);
            return "User registration successful.";
        }
    }

    }

