package com.cafeteria.cafeteria.ViewModels;

import javax.validation.constraints.*;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UpdateOrderStatus", description = "UpdateOrderStatus model")
public class UpdateOrderStatus {
    @NotNull
    public Long order_id;
    
    public String status;
}
