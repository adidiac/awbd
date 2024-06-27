package com.cafeteria.cafeteria.service.Impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.cafeteria.cafeteria.MyConstants;
import com.cafeteria.cafeteria.CustomExceptions.InternalServerErrorException;
import com.cafeteria.cafeteria.DbModels.Order;
import com.cafeteria.cafeteria.ViewModels.CreateOrder;
import com.cafeteria.cafeteria.ViewModels.UpdateOrderStatus;
import com.cafeteria.cafeteria.ViewModels.ViewOrder;
import com.cafeteria.cafeteria.repository.OrderRepository;
import com.cafeteria.cafeteria.repository.UserRepository;
import com.cafeteria.cafeteria.service.OrderItemService;
import com.cafeteria.cafeteria.service.OrderService;
import com.cafeteria.cafeteria.service.UserService;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderItemService orderItemService;
    private final UserService userService;
    public OrderServiceImpl(
        OrderRepository orderRepository,
        UserRepository userRepository,
        OrderItemService orderItemService,
        UserService userService
        ) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderItemService = orderItemService;
        this.userService = userService;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long order_id) {
        return orderRepository.findById(order_id).orElse(null);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteById(Long order_id) {
        orderRepository.deleteById(order_id);
    }

    @Override
    public List<ViewOrder> getAllOrders() {
        var orders = findAll();
        var viewOrders = new ArrayList<ViewOrder>();

        for (Order order : orders) {
            var viewOrder = new ViewOrder();
            viewOrder.username = userRepository.findById(order.getUser().getUserId()).orElse(null).getUsername();
            viewOrder.status = order.getOrderStatus();
            viewOrder.date = order.getOrderDate();
            viewOrder.menuItems = orderItemService.getAllOrderItemsByOrderId(order.getOrderId());
            viewOrder.totalPrice = String.valueOf(viewOrder.menuItems.stream().mapToDouble(x -> Double.valueOf(x.price)).sum());
            viewOrders.add(viewOrder);    
        }
        return viewOrders;
    }

    @Override
    public void createOrder(CreateOrder createOrder) {
        var order = new Order();
        order.setUser(userRepository.findById(userService.getUserIdByUsername(createOrder.username)).orElse(null));
        order.setOrderStatus("Pending");
        order.setDateFromLocalDate();
        save(order);
        createOrder.orderItems.forEach(orderItem -> {
            orderItemService.createOrderItem(orderItem, order.getOrderId());
        });
    }

    @Override
    public void updateOrderStatus(UpdateOrderStatus updateOrderStatus) {
        var order = findById(updateOrderStatus.order_id);
        if (order == null) {
            throw new InternalServerErrorException(MyConstants.ERROR_MESSAGE_ORDER_NOT_FOUND);
        }
        order.setOrderStatus(updateOrderStatus.status);
        save(order);
    }

    @Override
    public Integer getTotalPriceOfOrder(Long orderId) {
        var orderItems = orderItemService.getAllOrderItemsByOrderId(orderId);
        var totalPrice = 0;
        for (var orderItem : orderItems) {
            totalPrice += Integer.valueOf(orderItem.price);
        }
        return totalPrice;
    }
    public void deleteOrderWhenMenuItemsDeleted(Long menu_item_id) {
        var orders = findAll();
        for (var order : orders) {
            var orderItems = orderItemService.findByOrderId(order.getOrderId());
            for (var orderItem : orderItems) {
                if (orderItem.getMenuItemId() == menu_item_id) {
                    orderItemService.deleteById(orderItem.getId());
                }
            }
        }
    }
}
