package com.ecommerce.ecommercejpa.category;

import java.util.List;
import java.util.NoSuchElementException;

public interface CategoryServiceInterface {
    
    public List<CategoryModel> getCategories();

    public CategoryModel getCategoryById(Long id) throws NoSuchElementException;

    public CategoryModel createCategory(CategoryModel categoryJson);
}