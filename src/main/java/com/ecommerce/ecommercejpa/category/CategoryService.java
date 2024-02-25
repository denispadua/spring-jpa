package com.ecommerce.ecommercejpa.category;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.management.openmbean.KeyAlreadyExistsException;

import org.springframework.stereotype.Service;

import com.ecommerce.ecommercejpa.category.dto.CategoryRequestDto;

@Service
public class CategoryService implements CategoryServiceInterface {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Category> getCategories() {
        return repository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> result = repository.findById(id);
        if (result.isEmpty()) {
            throw new NoSuchElementException("Category not found");
        }
        return result.get();
    }

    public boolean categoryExists(String name) {
        Optional<Category> category = repository.findByName(name);
        if (category.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public Category createCategory(CategoryRequestDto request) {
        if (this.categoryExists(request.name())) {
            throw new KeyAlreadyExistsException("Category already exist");
        }

        Category newCategory = new Category();
        newCategory.setName(request.name());
        return repository.save(newCategory);
    }
}
