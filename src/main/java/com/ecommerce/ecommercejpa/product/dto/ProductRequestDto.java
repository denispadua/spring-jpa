package com.ecommerce.ecommercejpa.product.dto;

import com.ecommerce.ecommercejpa.category.Category;

import java.math.BigDecimal;

public record ProductRequestDto(String img, String name, String description,
        BigDecimal price, Integer quantity, Category category) {
}
