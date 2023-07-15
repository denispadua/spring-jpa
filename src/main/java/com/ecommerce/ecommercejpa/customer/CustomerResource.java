package com.ecommerce.ecommercejpa.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.ecommercejpa.customer.dto.CustomerLoginRequest;
import com.ecommerce.ecommercejpa.utils.ResponseHandler;

@RestController
@RequestMapping("/customer")
public class CustomerResource {
    
    private final CustomerService service;

    public CustomerResource(CustomerService service){
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> createCustomer(@RequestBody CustomerModel customer){
        return ResponseHandler.response(service.createCustomer(customer), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody CustomerLoginRequest customer){
        return ResponseHandler.response(service.login(customer.getEmail()), HttpStatus.OK);
    }
}
