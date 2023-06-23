package com.ecommerce.ecommercejpa.product;

import java.util.List;
import java.util.NoSuchElementException;

public interface ProductServiceInterface {

    public List<ProductModel> getProducts();

    public ProductModel getProductById(Long id) throws NoSuchElementException;

    public ProductModel createProduct(ProductModel productJson);
}
