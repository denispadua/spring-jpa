package com.ecommerce.ecommercejpa.order.dto;

import java.math.BigDecimal;

import com.ecommerce.ecommercejpa.product.Product;

public class Item {
    private Product product;
    private Integer quantity;
    private BigDecimal price;

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
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
