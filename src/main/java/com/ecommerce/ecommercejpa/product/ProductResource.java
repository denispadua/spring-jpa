package com.ecommerce.ecommercejpa.product;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.ecommercejpa.utils.ResponseHandler;

@RestController
@RequestMapping("/product")
public class ProductResource {

    @Autowired
    private ProductService service;
    
    @GetMapping("/")
    public ResponseEntity<Object> getProducts(){
        return ResponseHandler.response(service.getProducts(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id){
        try {
            return ResponseHandler.response(service.getProductById(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }
}