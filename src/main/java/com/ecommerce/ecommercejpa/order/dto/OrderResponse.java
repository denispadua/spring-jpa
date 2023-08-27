package com.ecommerce.ecommercejpa.order.dto;

import java.math.BigDecimal;
import java.util.List;


public class OrderResponse {
    private List<Item> items;
    private BigDecimal orderValue;

    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }
}
