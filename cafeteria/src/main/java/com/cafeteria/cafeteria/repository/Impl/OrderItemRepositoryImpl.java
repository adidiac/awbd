package com.cafeteria.cafeteria.repository.Impl;


import com.cafeteria.cafeteria.DbModels.OrderItem;
import com.cafeteria.cafeteria.repository.OrderItemRepository;
import java.util.List;


public abstract class OrderItemRepositoryImpl implements OrderItemRepository {

    @Override
    public List<OrderItem> findByOrderId(Long order_id) {
        return findAll().stream().filter( orderItem -> orderItem.getOrderId().equals(order_id)).toList();   
    }
}