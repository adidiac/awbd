package com.cafeteria.cafeteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafeteria.cafeteria.DbModels.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // Additional query methods if needed
    
}
