package com.ecommerce.ecommercejpa.order.dto;

import java.util.List;

import com.ecommerce.ecommercejpa.customer.CustomerModel;

public class OrderRequest {
    private CustomerModel customer;
    private List<Item> items;

    public CustomerModel getCustomer() {
        return customer;
    }
    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }
    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }
}
