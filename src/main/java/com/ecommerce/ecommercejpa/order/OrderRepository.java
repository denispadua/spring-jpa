package com.ecommerce.ecommercejpa.order;

import org.springframework.data.repository.ListCrudRepository;

public interface OrderRepository extends ListCrudRepository<OrderModel, Long>{
    
}