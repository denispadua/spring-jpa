package com.ecommerce.ecommercejpa.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommercejpa.utils.ResponseHandler;

@RestController
@RequestMapping("/product")
public class ProductResource {

    @Autowired
    private ProductService service;
    
    @GetMapping("/")
    public ResponseEntity<Object> createProduct(){
        List<ProductModel> lsProduct = service.getProducts();
        return ResponseHandler.response(lsProduct,HttpStatus.CREATED);
    }
}