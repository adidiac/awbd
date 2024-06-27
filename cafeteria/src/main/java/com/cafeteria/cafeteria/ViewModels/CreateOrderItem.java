package com.cafeteria.cafeteria.ViewModels;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
@Schema(name = "CreateOrderItem", description = "CreateOrderItem model")
public class CreateOrderItem {
    @NotNull
    public Long menuItemId;
    @NotNull
    @Min(1)
    public Integer quantity;
    @Size(max = 1000)
    public String specialInstructions;
}
