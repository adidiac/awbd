package com.cafeteria.cafeteria.ViewModels;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ViewOrder", description = "ViewOrder model")
public class ViewOrder {
    @NotBlank
    public String username;
    @NotBlank
    public String status;
    public List<ViewOrderItem> menuItems;
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])\\/(0[1-9]|1[012])\\/\\d{4}$", message = "Date must be in the format dd/mm/yyyy")
    public String date;
    @Min(value = 0, message = "Total price must be a positive number")
    public String totalPrice;
}