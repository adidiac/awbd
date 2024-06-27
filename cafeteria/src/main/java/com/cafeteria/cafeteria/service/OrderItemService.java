package com.cafeteria.cafeteria.service;

import java.util.List;

import com.cafeteria.cafeteria.DbModels.OrderItem;
import com.cafeteria.cafeteria.ViewModels.CreateOrderItem;
import com.cafeteria.cafeteria.ViewModels.ViewOrderItem;

public interface OrderItemService {

    List<OrderItem> findAll();
    OrderItem findById(Long order_item_id);
    OrderItem save(OrderItem order_item);
    void deleteById(Long order_item_id);
    List<OrderItem> findByOrderId(Long order_id);
    List<ViewOrderItem> getAllOrderItems();
    List<ViewOrderItem> getAllOrderItemsByOrderId(Long order_id);
    void createOrderItem(CreateOrderItem createOrderItem, Long order_id);

}
