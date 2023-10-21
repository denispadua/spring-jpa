package com.ecommerce.ecommercejpa.order;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import jakarta.transaction.Transactional;


public interface OrderRepository extends ListCrudRepository<OrderModel, Long>{
    @Query("SELECT quantity FROM ProductModel P WHERE P.id = :productId")
    Integer getProductQuantityById(Long productId);

    @Transactional
    @Query("update ProductModel P SET P.quantity = :quantity where P.id = :productId")
    Void updateProductQuantity(Integer quantity, Long productId);

}