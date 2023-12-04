package com.ecommerce.ecommercejpa.category;

import java.util.List;
import java.util.NoSuchElementException;

public interface CategoryServiceInterface {
    
    public List<Category> getCategories();

    public Category getCategoryById(Long id) throws NoSuchElementException;

    public Category createCategory(Category categoryJson);
}