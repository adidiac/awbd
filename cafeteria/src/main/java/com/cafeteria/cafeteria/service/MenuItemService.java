package com.cafeteria.cafeteria.service;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cafeteria.cafeteria.DbModels.MenuItem;
import com.cafeteria.cafeteria.ViewModels.MenuItemDto;

public interface MenuItemService {

    List<MenuItem> getAllMenuItems();

    MenuItem getMenuItemById(Long id);

    MenuItem createMenuItem(MenuItem menuItem);

    MenuItem updateMenuItem(Long id, MenuItem menuItem);

    void deleteMenuItem(Long id);

    MenuItem getMenuItemByName(String name);

    Page<MenuItemDto> getAllMenuItems(Pageable pageable);
}
