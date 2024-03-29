package com.ecommerce.ecommercejpa.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommercejpa.customer.dto.CustomerLoginRequestDto;
import com.ecommerce.ecommercejpa.customer.dto.CustomerRegisterRequestDto;
import com.ecommerce.ecommercejpa.utils.JwtUtils;
import com.ecommerce.ecommercejpa.utils.ResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerResource {

    private final CustomerService service;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public CustomerResource(CustomerService service, AuthenticationManager authenticationManager, JwtUtils jwtUtils){
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> createCustomer(@RequestBody @Valid CustomerRegisterRequestDto request){
        return ResponseHandler.response(service.createCustomer(request), HttpStatus.OK, null);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid CustomerLoginRequestDto request){
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseHandler.response(jwtUtils.generateJwtToken(authentication), HttpStatus.OK, null);
    }
}
