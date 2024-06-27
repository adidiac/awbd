package com.cafeteria.cafeteria.service;

import java.util.List;

import com.cafeteria.cafeteria.DbModels.Order;
import com.cafeteria.cafeteria.ViewModels.CreateOrder;
import com.cafeteria.cafeteria.ViewModels.UpdateOrderStatus;
import com.cafeteria.cafeteria.ViewModels.ViewOrder;

public interface OrderService {
    
    List<Order> findAll();
    Order findById(Long order_id);
    Order save(Order order);
    void deleteById(Long order_id);

    List<ViewOrder> getAllOrders();
    void createOrder(CreateOrder createOrder);
    void updateOrderStatus(UpdateOrderStatus updateOrderStatus);
    void deleteOrderWhenMenuItemsDeleted(Long menu_item_id);

    Integer getTotalPriceOfOrder(Long orderId);
}
