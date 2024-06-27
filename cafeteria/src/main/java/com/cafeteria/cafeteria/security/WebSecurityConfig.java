package com.cafeteria.cafeteria.security;

import com.cafeteria.cafeteria.Aspects.TokenValidationAspect;
import com.cafeteria.cafeteria.Utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Bean
    public TokenValidationAspect tokenValidationAspect() {
        return new TokenValidationAspect(jwtTokenUtil);
    }

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter(jwtTokenUtil);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/public/**").permitAll() // Public routes
            .requestMatchers("/api/**").authenticated() // Protected routes
            .anyRequest().permitAll() // Allow all other routes
        )
        .addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
