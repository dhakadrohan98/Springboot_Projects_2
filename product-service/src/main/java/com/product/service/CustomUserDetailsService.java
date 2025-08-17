package com.product.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Dummy: In real app, fetch from DB or shared service. Here, assume valid if username exists.
        return new User(username, "", true, true, true, true,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))); // No password check needed for JWT validation
    }
}
