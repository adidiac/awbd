package com.cafeteria.cafeteria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafeteria.cafeteria.DbModels.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderId(Long order_id);
    
}