package com.cafeteria.cafeteria.ViewModels;


import com.cafeteria.cafeteria.DbModels.MenuItem;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ViewMenuItem", description = "ViewMenuItem model")
public class MenuItemDto {
    public Long id;
    public String name;
    public double price;
    public String description;
    public String category;

    public MenuItemDto() {
    }

    public MenuItemDto(Long id, String name, double price, String description, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public MenuItemDto(MenuItem menuItem) {
        this.id = menuItem.getId();
        this.name = menuItem.getName();
        this.price = menuItem.getPrice();
        this.description = menuItem.getDescription();
        this.category = menuItem.getCategory();
    }
}
