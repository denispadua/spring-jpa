package com.ecommerce.ecommercejpa.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository repository;

    public List<ProductModel> getProducts(){
        return repository.findAll();
    }

    public ProductModel getProductById(Long id){
        return repository.findById(id).orElse(new ProductModel());
    }
}
