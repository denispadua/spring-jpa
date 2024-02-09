package com.ecommerce.ecommercejpa.order.dto;

import java.util.List;

import com.ecommerce.ecommercejpa.customer.dto.CustomerData;


public class OrderResponse {
    private Long id;
    private CustomerData customer;
    private List<ItemResponse> items;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public CustomerData getCustomer() {
        return customer;
    }
    public void setCustomer(CustomerData customer) {
        this.customer = customer;
    }
    public List<ItemResponse> getItems() {
        return items;
    }
    public void setItems(List<ItemResponse> items) {
        this.items = items;
    }   
}
