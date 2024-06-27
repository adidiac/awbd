
package com.cafeteria.cafeteria.service.Impl;
import com.cafeteria.cafeteria.MyConstants;
import com.cafeteria.cafeteria.CustomExceptions.InternalServerErrorException;
import com.cafeteria.cafeteria.DbModels.MenuItem;
import com.cafeteria.cafeteria.ViewModels.MenuItemDto;
import com.cafeteria.cafeteria.repository.MenuItemRepository;
import com.cafeteria.cafeteria.service.MenuItemService;
import com.cafeteria.cafeteria.service.PromotionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final PromotionService promotionService;

    public MenuItemServiceImpl(
        MenuItemRepository menuItemRepository, 
        PromotionService promotionService
        ) {
        this.menuItemRepository = menuItemRepository;
        this.promotionService = promotionService;
    }

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @Override
    public List<MenuItem> getAllMenuItems() {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        return circuitBreaker.run(() -> menuItemRepository.findAll(), throwable -> fallbackGetAllMenuItems());
    }

    public List<MenuItem> fallbackGetAllMenuItems() {
        return new ArrayList<>();
    }

    @Override
    public MenuItem getMenuItemById(Long id) {
        return menuItemRepository.findById(id).orElse(null);
    }

    @Override
    public MenuItem createMenuItem(MenuItem menuItem) {
        if (this.getMenuItemByName(menuItem.getName()) != null) {
            throw new InternalServerErrorException(MyConstants.ERROR_MESSAGE_MENU_ITEM_ALREADY_EXISTS);
        }
        return menuItemRepository.save(menuItem);
    }

    @Override
    public MenuItem updateMenuItem(Long id, MenuItem menuItem) {
        MenuItem existingMenuItem = menuItemRepository.findById(id).orElse(null);
        if (existingMenuItem != null) {
            // Update the properties you want to allow modification
            existingMenuItem.setName(menuItem.getName());
            existingMenuItem.setDescription(menuItem.getDescription());
            existingMenuItem.setPrice(menuItem.getPrice());
            existingMenuItem.setCategory(menuItem.getCategory());

            return menuItemRepository.save(existingMenuItem);
        }
        throw new InternalServerErrorException(MyConstants.ERROR_MESSAGE_MENU_ITEM_NOT_FOUND);
    }

    @Override
    public void deleteMenuItem(Long id) {
        MenuItem existingMenuItem = menuItemRepository.findById(id).orElse(null);
        if (existingMenuItem == null) {
            throw new InternalServerErrorException(MyConstants.ERROR_MESSAGE_MENU_ITEM_NOT_FOUND);
        }
        promotionService.deletePromotionByMenuItemId(id);
        menuItemRepository.deleteById(id);
    }

    @Override
    public MenuItem getMenuItemByName(String name) {
        return menuItemRepository.findAll().stream().filter(menuItem -> menuItem.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Page<MenuItemDto> getAllMenuItems(Pageable pageable) {
        return menuItemRepository.findAll(pageable).map(MenuItemDto::new);
    }
}
