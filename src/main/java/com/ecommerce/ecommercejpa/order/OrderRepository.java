package com.ecommerce.ecommercejpa.order;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import jakarta.transaction.Transactional;


public interface OrderRepository extends ListCrudRepository<Order, Long>{
    @Query("SELECT quantity FROM Product P WHERE P.id = :productId")
    Integer getProductQuantityById(Long productId);

    @Transactional
    @Query("update Product P SET P.quantity = :quantity where P.id = :productId")
    Void updateProductQuantity(Integer quantity, Long productId);
    
    List<Order> findAllOrderByCustomerId(Long id);
}