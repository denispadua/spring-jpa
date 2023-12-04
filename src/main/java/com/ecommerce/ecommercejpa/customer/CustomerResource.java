package com.ecommerce.ecommercejpa.customer;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.ecommerce.ecommercejpa.customer.dto.CustomerLoginRequest;
import com.ecommerce.ecommercejpa.utils.JwtUtils;
import com.ecommerce.ecommercejpa.utils.ResponseHandler;

@RestController
@RequestMapping("/customer")
public class CustomerResource {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    private final CustomerService service;
    
    public CustomerResource(CustomerService service){
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> createCustomer(@RequestBody Customer customer){
        return ResponseHandler.response(service.createCustomer(customer), HttpStatus.OK, null);
    }

    @PostMapping("/login")
    public String login(@RequestBody CustomerLoginRequest customer){
        
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(customer.getUsername(), customer.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        
        return jwt;
        // return ResponseHandler.response(service.login(customer.getEmail()), HttpStatus.OK);
    }
}
