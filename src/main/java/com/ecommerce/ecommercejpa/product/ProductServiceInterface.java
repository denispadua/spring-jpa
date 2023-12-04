package com.ecommerce.ecommercejpa.product;

import java.util.List;
import java.util.NoSuchElementException;

public interface ProductServiceInterface {

    public List<Product> getProducts();

    public Product getProductById(Long id) throws NoSuchElementException;

    public Product createProduct(Product productJson);
}
