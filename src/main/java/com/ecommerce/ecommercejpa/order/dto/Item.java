package com.ecommerce.ecommercejpa.order.dto;

import java.math.BigDecimal;

import com.ecommerce.ecommercejpa.product.ProductModel;

public class Item {
    private ProductModel product;
    private Integer quantity;
    private BigDecimal price;

    public ProductModel getProduct() {
        return product;
    }
    public void setProduct(ProductModel product) {
        this.product = product;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
