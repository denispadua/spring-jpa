package com.ecommerce.ecommercejpa.product;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements ProductServiceInterface{
    
    private final ProductRepository repository;

    public ProductService(ProductRepository repository){
        this.repository = repository;
    }
    @Override
    public List<ProductModel> getProducts(){
        return repository.findAll();
    }
    @Override
    public ProductModel getProductById(Long id) throws NoSuchElementException{
        return repository.findById(id).get();
    }
    @Override
    public ProductModel createProduct(ProductModel productJson){
        ProductModel newProduct = new ProductModel();
        BeanUtils.copyProperties(productJson, newProduct);
        return repository.save(newProduct);
    }

    public ProductModel updateProductQuantity(Long id, Integer quantity){
        Optional<ProductModel> p = repository.findById(id);
        if(p.isPresent()){
            p.get().setQuantity(p.get().getQuantity() - quantity);
            return repository.save(p.get());
        }
        return null;
    }
}
