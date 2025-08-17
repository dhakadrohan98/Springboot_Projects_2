package com.product.config;

import com.product.filter.JwtAuthFilter;
import com.product.util.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public JwtAuthFilter jwtAuthFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        return new JwtAuthFilter(jwtUtil, userDetailsService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
        http.
                csrf(csrf -> csrf.disable()) // Disable CSRF
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()) //secure all endpoints
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //stateless
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter
        return http.build();
    }
}
