package com.ecommerce.ecommercejpa.product;

import java.util.List;
import java.util.NoSuchElementException;

import com.ecommerce.ecommercejpa.product.dto.ProductRequestDto;

public interface ProductServiceInterface {

    public List<Product> getProducts();

    public Product getProductById(Long id) throws NoSuchElementException;

    public Product createProduct(ProductRequestDto request);
}
