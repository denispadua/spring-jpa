package com.ecommerce.ecommercejpa.product;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    
    private final ProductRepository repository;

    public ProductService(ProductRepository repository){
        this.repository = repository;
    }

    public List<ProductModel> getProducts(){
        return repository.findAll();
    }

    public ProductModel getProductById(Long id) throws NoSuchElementException{
        return repository.findById(id).get();
    }

    public ProductModel createProduct(ProductModel productJson){
        ProductModel newProduct = new ProductModel();
        BeanUtils.copyProperties(productJson, newProduct);
        return repository.save(newProduct);
    }
}
