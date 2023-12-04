package com.ecommerce.ecommercejpa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtSecretService {
    @Value("${secret}")
    private String jwtSecret;

    @Value("${tokenExpirationTime}")
    private Integer tokenExpirationTime;

    public String getJwtSecret() {
        return jwtSecret;
    }

    public Integer getTokenExpirationTime(){
        return tokenExpirationTime;
    }
}
