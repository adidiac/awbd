package com.cafeteria.cafeteria.ViewModels;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ViewOrderItem", description = "ViewOrderItem model")    
public class ViewOrderItem {
    @NotBlank
    public String name;
    @NotBlank
    @Size(max = 1000)
    public String description;
    @Min(0)
    public String price;    
    @Min(1)
    public String quantity;
    @NotBlank
    public String category;
    @Min(1)
    public Long menuItemId;
    @Size(max = 1000)
    public String specialInstructions;
}
