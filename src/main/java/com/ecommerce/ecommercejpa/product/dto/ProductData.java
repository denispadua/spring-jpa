package com.ecommerce.ecommercejpa.product.dto;

import com.ecommerce.ecommercejpa.category.Category;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ProductData {

    private Long id;
    private String img;
    private String name;
    private String description;
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="category_id")
    private Category category;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
}
