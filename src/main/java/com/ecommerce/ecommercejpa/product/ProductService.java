package com.ecommerce.ecommercejpa.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository repository;

    public List<ProductModel> getProducts(){
        Iterable<ProductModel> data =  repository.findAll();
        List<ProductModel> lsData = new ArrayList<ProductModel>();
        data.forEach(lsData::add);
        return lsData;
    }
}
