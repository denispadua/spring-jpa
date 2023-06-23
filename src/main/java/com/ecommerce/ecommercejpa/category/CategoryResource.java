package com.ecommerce.ecommercejpa.category;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.ecommercejpa.utils.ResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryResource {
    
    @Autowired
    private CategoryService service;

    @GetMapping("/")
    public ResponseEntity<Object> getProducts(){
        return ResponseHandler.response(service.getCategories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id){
        try {
            return ResponseHandler.response(service.getCategoryById(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> createProduct(@RequestBody @Valid CategoryModel jsonProduct){
        try {
            return ResponseHandler.response(service.createCategory(jsonProduct), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to create a category");
        }
    }
}
