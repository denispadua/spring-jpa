package com.ecommerce.ecommercejpa.category;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommercejpa.utils.ResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryResource {
    
    private final CategoryService service;

    public CategoryResource(CategoryService service){
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<Object> getProducts(){
        return ResponseHandler.response(service.getCategories(), HttpStatus.OK, null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id){
        return ResponseHandler.response(service.getCategoryById(id), HttpStatus.OK, null);
    }
    @PostMapping("/")
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<Object> createProduct(@RequestBody @Valid Category jsonProduct){
        return ResponseHandler.response(service.createCategory(jsonProduct), HttpStatus.CREATED, null);
    }
}
