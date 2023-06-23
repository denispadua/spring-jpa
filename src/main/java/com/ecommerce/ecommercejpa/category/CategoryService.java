package com.ecommerce.ecommercejpa.category;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository repository;

    public List<CategoryModel> getCategories(){
        return repository.findAll();
    }

    public CategoryModel getCategoryById(Long id) throws NoSuchElementException{
        return repository.findById(id).get();
    }

    public CategoryModel createCategory(CategoryModel categoryJson){
        CategoryModel newCategory = new CategoryModel();
        BeanUtils.copyProperties(categoryJson, newCategory);
        return repository.save(newCategory);
    }
}