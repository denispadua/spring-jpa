package com.ecommerce.ecommercejpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz
            .anyRequest()
            .permitAll());
        http.csrf(cors -> cors.disable());
        http.cors(httpSecurityCorsConfigurer -> 
        httpSecurityCorsConfigurer.configurationSource(request -> 
           new CorsConfiguration().applyPermitDefaultValues()
        )
    );
        return http.build();
    }
}
