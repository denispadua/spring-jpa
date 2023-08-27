package com.ecommerce.ecommercejpa.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommercejpa.customer.CustomerModel;
import com.ecommerce.ecommercejpa.order.dto.Item;
import com.ecommerce.ecommercejpa.product.ProductService;

@Service
public class OrderService {
        
    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductService productService;

    public boolean isItemAvailable(Item item){
        Integer quantity = repository.getProductQuantityById(item.getProduct().getId());
        if(quantity >= item.getQuantity()){
            return true;
        }
        return false;
    }
    
    public List<Item> createOrder(List<Item> items, CustomerModel customer){
        OrderModel order = new OrderModel();
        List<Item> itemsUnavailable = new ArrayList<Item>();
        order.setCustomer(customer);
        items.forEach(item -> {
            if(isItemAvailable(item)){
                productService.updateProductQuantity(item.getProduct().getId(), item.getQuantity());
                order.setPrice(item.getPrice());
                order.setProduct(item.getProduct());
                order.setQuantity(item.getQuantity());
                repository.save(order);
            }
            else{
                itemsUnavailable.add(item);
            }
        });
        return itemsUnavailable;
    }
}
