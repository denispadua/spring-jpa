package com.ecommerce.ecommercejpa.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommercejpa.utils.ResponseHandler;

@RestController
@RequestMapping("/order")
public class OrderResource {
    
    @Autowired
    private OrderRepository repository;
    
    @GetMapping("/")
    public ResponseEntity<Object> getOrders(){
        List<OrderModel> orders = repository.findAll();
        return ResponseHandler.response(orders, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Object> CreateOrders(@RequestBody OrderModel newOrder){
        OrderModel order = repository.save(newOrder);
        return ResponseHandler.response(order, HttpStatus.OK);
    }
}
