package com.ecommerce.ecommercejpa.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ecommerce.ecommercejpa.config.JwtSecretService;
import com.ecommerce.ecommercejpa.customer.Customer;

@Component
public class JwtUtils {

        @Autowired
        private JwtSecretService jwtSecret;
        
        public String generateJwtToken(Authentication authentication) {

        Customer userPrincipal = (Customer) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getJwtSecret());
        Date createdDate = new Date();
        return JWT.create()
                .withSubject(userPrincipal.getEmail())
                .withIssuedAt(createdDate)
                .withExpiresAt(new Date(createdDate.getTime() + jwtSecret.getTokenExpirationTime()))
                .sign(algorithm);
    }
}
