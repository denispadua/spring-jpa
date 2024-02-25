package com.ecommerce.ecommercejpa.category;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

public interface CategoryRepository extends ListCrudRepository<Category, Long>{

    Optional<Category> findByName(String name);
}
