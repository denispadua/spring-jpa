package com.ecommerce.ecommercejpa.utils;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ecommerce.ecommercejpa.customer.CustomerModel;

@Component
public class JwtUtils {
        public String generateJwtToken(Authentication authentication) {

        CustomerModel userPrincipal = (CustomerModel) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("jsdfnjdsnjfnds");
        return JWT.create()
                // .withSubject(userPrincipal.getUsername())
                // .withIssuedAt(new Date())
                // .withExpiresAt(new Date((new Date()).getTime() + 5000))
                .withIssuer("issuer")
                .withSubject(userPrincipal.getEmail())
                .sign(algorithm);
    }
}
