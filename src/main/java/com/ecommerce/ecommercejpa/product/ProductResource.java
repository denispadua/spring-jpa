package com.ecommerce.ecommercejpa.product;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.ecommercejpa.product.dto.ProductRequestDto;
import com.ecommerce.ecommercejpa.utils.ResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductResource {

    private final ProductService service;
    
    public ProductResource(ProductService service){
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<Object> getProducts(){
        return ResponseHandler.response(service.getProducts(), HttpStatus.OK, null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id){
        try {
            return ResponseHandler.response(service.getProductById(id), HttpStatus.OK, null);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> createProduct(@RequestBody @Valid ProductRequestDto request){
        try {
            return ResponseHandler.response(service.createProduct(request), HttpStatus.CREATED, null);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to create a user");
        }
    }
}