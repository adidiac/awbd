package com.cafeteria.cafeteria.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.cafeteria.Aspects.ValidateTokenAdmin;
import com.cafeteria.cafeteria.Aspects.ValidateTokenUser;
import com.cafeteria.cafeteria.ViewModels.CreateOrder;
import com.cafeteria.cafeteria.ViewModels.UpdateOrderStatus;
import com.cafeteria.cafeteria.ViewModels.ViewOrder;
import com.cafeteria.cafeteria.service.OrderService;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;



@RestController
@RequestMapping("/public/orders")
@Tag(name = "Orders", description = "Orders API for creating, getting, updating and deleting orders")
public class OrderController {
    
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @ValidateTokenAdmin
    public ResponseEntity<List<ViewOrder>> getAllOrders(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
    

    @PostMapping
    @Valid
    public ResponseEntity<Void> createOrder(@RequestBody CreateOrder createOrder, @RequestHeader("Authorization") String token) {
        orderService.createOrder(createOrder);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update-status")
    @ValidateTokenAdmin
    @Valid
    public ResponseEntity<Void> updateOrderStatus(@RequestBody UpdateOrderStatus entity, @RequestHeader("Authorization") String token) {
        orderService.updateOrderStatus(entity);
        return ResponseEntity.ok().build();
    }
    

}
