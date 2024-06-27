package com.cafeteria.cafeteria.ViewModels;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
@Schema(name = "CreateOrder", description = "CreateOrder model")
public class CreateOrder extends UserAuthModel {
    public List<CreateOrderItem> orderItems;
}
