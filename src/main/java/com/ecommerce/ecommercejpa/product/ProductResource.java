package com.ecommerce.ecommercejpa.product;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommercejpa.category.CategoryModel;
import com.ecommerce.ecommercejpa.utils.ResponseHandler;

@RestController
@RequestMapping("/product")
public class ProductResource {

    @Autowired
    private ProductRepository repo;
    
    @GetMapping("/")
    public ResponseEntity<Object> createProduct(){
        ProductModel product = new ProductModel();
        CategoryModel c = new CategoryModel();
        c.setName("TESTE CATEGORIA JPA");
        product.setDescription("Data from spring jpa");
        product.setImg("img url from spring jpa");
        product.setName("product name from spring jpa");
        product.setPrice(new BigDecimal(20));
        product.setCategory(c);
        repo.save(product);
        return ResponseHandler.response(product,HttpStatus.CREATED);
    }
}