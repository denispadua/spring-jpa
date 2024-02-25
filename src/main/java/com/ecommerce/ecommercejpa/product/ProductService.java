package com.ecommerce.ecommercejpa.product;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommercejpa.product.dto.ProductRequestDto;

@Service
public class ProductService implements ProductServiceInterface{
    
    private final ProductRepository repository;

    public ProductService(ProductRepository repository){
        this.repository = repository;
    }
    @Override
    public List<Product> getProducts(){
        return repository.findAll();
    }
    @Override
    public Product getProductById(Long id) throws NoSuchElementException{
        return repository.findById(id).get();
    }
    @Override
    public Product createProduct(ProductRequestDto request){
        Product newProduct = new Product();
        BeanUtils.copyProperties(request, newProduct);
        return repository.save(newProduct);
    }

    public Product updateProductQuantity(Long id, Integer quantity){
        Optional<Product> p = repository.findById(id);
        if(p.isPresent()){
            p.get().setQuantity(p.get().getQuantity() - quantity);
            return repository.save(p.get());
        }
        return null;
    }
}
