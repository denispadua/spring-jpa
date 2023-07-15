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

    public List<CategoryModel> getCategories(){
        return repository.findAll();
    }

    public CategoryModel getCategoryById(Long id){
        Optional<CategoryModel> result = repository.findById(id);
        if(result.isEmpty()){
            throw new NoSuchElementException("Category not found");
        }
        return result.get();
    }

    public CategoryModel createCategory(CategoryModel categoryJson){
        Optional<CategoryModel> result = repository.findById(categoryJson.getId());
        if(result.isPresent()){
            throw new KeyAlreadyExistsException("Category already exist");
        }
        CategoryModel newCategory = new CategoryModel();
        BeanUtils.copyProperties(categoryJson, newCategory);
        return repository.save(newCategory);
    }
}
