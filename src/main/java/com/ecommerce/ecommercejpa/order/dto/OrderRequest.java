package com.ecommerce.ecommercejpa.order.dto;

import java.util.List;

import com.ecommerce.ecommercejpa.customer.Customer;

public class OrderRequest {
    private Customer customer;
    private List<Item> items;

    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }
}
