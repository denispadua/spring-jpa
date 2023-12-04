package com.ecommerce.ecommercejpa.category;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.management.openmbean.KeyAlreadyExistsException;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements CategoryServiceInterface{
    
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository){
        this.repository = repository;
    }
    @Override
    public List<Category> getCategories(){
        return repository.findAll();
    }
    @Override
    public Category getCategoryById(Long id){
        Optional<Category> result = repository.findById(id);
        if(result.isEmpty()){
            throw new NoSuchElementException("Category not found");
        }
        return result.get();
    }
    @Override
    public Category createCategory(Category categoryJson){
        Optional<Category> result = repository.findById(categoryJson.getId());
        if(result.isPresent()){
            throw new KeyAlreadyExistsException("Category already exist");
        }
        Category newCategory = new Category();
        BeanUtils.copyProperties(categoryJson, newCategory);
        return repository.save(newCategory);
    }
}
