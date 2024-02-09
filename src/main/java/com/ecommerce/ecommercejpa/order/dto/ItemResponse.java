package com.ecommerce.ecommercejpa.order.dto;

import java.math.BigDecimal;

import com.ecommerce.ecommercejpa.product.dto.ProductData;

public class ItemResponse {
    private ProductData product;
    private Integer quantity;
    private BigDecimal price;

    public ProductData getProduct() {
        return product;
    }
    public void setProduct(ProductData product) {
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
