package com.ecommerce.ecommercejpa.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ecommerce.ecommercejpa.customer.CustomerModel;

@Component
public class JwtUtils {

        @Value("${secret}")
        private String jwtSecret;

        @Value("${tokenExpirationTime}")
        private Integer tokenExpirationTime;
        
        public String generateJwtToken(Authentication authentication) {

        CustomerModel userPrincipal = (CustomerModel) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        Date createdDate = new Date();
        return JWT.create()
                .withSubject(userPrincipal.getEmail())
                .withIssuedAt(createdDate)
                .withExpiresAt(new Date(createdDate.getTime() + tokenExpirationTime))
                .sign(algorithm);
    }
}
