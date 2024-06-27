package com.cafeteria.cafeteria.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cafeteria.cafeteria.service.AnalyticsService;
import com.cafeteria.cafeteria.service.MenuItemService;
import com.cafeteria.cafeteria.service.OrderItemService;
import com.cafeteria.cafeteria.service.OrderService;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    private final MenuItemService menuItemService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    public AnalyticsServiceImpl(MenuItemService menuItemService, OrderService orderService,
            OrderItemService orderItemService) {
        this.menuItemService = menuItemService;
        this.orderService = orderService;
        this.orderItemService = orderItemService;
    }

    @Override
    public HashMap<String,Integer> getMostPopularItems() {
        var orderItems = orderItemService.getAllOrderItems();
        var menuItems = menuItemService.getAllMenuItems();
        var menuItemsFromOrderItems = orderItems.stream().map(orderItem -> orderItem.menuItemId).toList();
        var menuItemsFromOrderItemsNames = menuItemsFromOrderItems.stream().map(menuItemId -> menuItemService.getMenuItemById(menuItemId).getName()).toList();
        var dictionaryMenuItems = new HashMap<String, Integer>();
        for (var menuItem : menuItems) {
            dictionaryMenuItems.put(menuItem.getName(), 0);
        }

        for (var menuItem : menuItemsFromOrderItemsNames) {
            dictionaryMenuItems.put(menuItem, dictionaryMenuItems.get(menuItem) + 1);
        }

        dictionaryMenuItems.entrySet().stream().sorted(Map.Entry.comparingByValue());

        return dictionaryMenuItems;
    }

    @Override
    public List<String> getMostProfitableDates() {
        var orders = orderService.findAll();

        var dictionaryOrders = new HashMap<String, Integer>();
        for (var order : orders) {
            dictionaryOrders.put(order.getOrderDate().toString(), 0);
        }

        for (var order : orders) {
            dictionaryOrders.put(order.getOrderDate().toString(), dictionaryOrders.get(order.getOrderDate().toString()) + orderService.getTotalPriceOfOrder(order.getOrderId()));
        }

        dictionaryOrders.entrySet().stream().sorted(Map.Entry.comparingByValue());

        return dictionaryOrders.keySet().stream().toList();
    }
    
}
