package com.ecommerce.ecommercejpa.category;

import java.util.List;
import java.util.NoSuchElementException;

import com.ecommerce.ecommercejpa.category.dto.CategoryRequestDto;

public interface CategoryServiceInterface {
    
    public List<Category> getCategories();

    public Category getCategoryById(Long id) throws NoSuchElementException;

    public Category createCategory(CategoryRequestDto request);
}