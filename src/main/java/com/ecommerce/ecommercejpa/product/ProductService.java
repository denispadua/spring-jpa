package com.ecommerce.ecommercejpa.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository repository;

    public List<ProductModel> getProducts(){
        Iterable<ProductModel> result =  repository.findAll();
        List<ProductModel> lsProduct = new ArrayList<ProductModel>();
        result.forEach(lsProduct::add);
        return lsProduct;
    }

    public ProductModel getProductById(Long id){
        Optional<ProductModel> result = repository.findById(id);
        ProductModel product = new ProductModel();
        if(result.isPresent()){
            product = result.get();
        }
        return product;
    }
}
