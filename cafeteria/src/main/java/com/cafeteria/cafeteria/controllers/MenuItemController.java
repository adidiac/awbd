
package com.cafeteria.cafeteria.controllers;

import com.cafeteria.cafeteria.Aspects.ValidateTokenAdmin;
import com.cafeteria.cafeteria.DbModels.MenuItem;
import com.cafeteria.cafeteria.ViewModels.MenuItemDto;
import com.cafeteria.cafeteria.service.MenuItemService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import javax.validation.Valid;

@Controller
@RequestMapping("/public/menu-items")
@Tag(name = "Menu Items", description = "Menu Items API for creating, getting, updating and deleting menu items")
public class MenuItemController {

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        return ResponseEntity.ok().body(menuItemService.getAllMenuItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
        return ResponseEntity.ok().body(menuItemService.getMenuItemById(id));
    }

    @PostMapping
    @ValidateTokenAdmin
    @Valid
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItem menuItem, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(menuItemService.createMenuItem(menuItem));
    }

    @PutMapping("/{id}")
    @ValidateTokenAdmin
     @Valid
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Long id, @RequestBody MenuItem menuItem, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(menuItemService.updateMenuItem(id, menuItem));
    }

    @DeleteMapping("/{id}")
    @ValidateTokenAdmin
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        menuItemService.deleteMenuItem(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/menuItems") 
    public String getMenuItems(Model model) {
        model.addAttribute("menuItems", menuItemService.getAllMenuItems(PageRequest.of(0, 10)));
        return "menuItems/menuItems"; // Thymeleaf template name
    }

    @GetMapping("/menuItemsPageable")
    public String getMenuItemsPageable(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            Model model) {
        
        Page<MenuItemDto> menuItemsPage = menuItemService.getAllMenuItems(PageRequest.of(page, size, Sort.by(sortBy)));
        model.addAttribute("menuItems", menuItemsPage);
        
        return "menuItems/menuItems"; // Thymeleaf template name
    }

    @GetMapping("/add")
    public String showAddMenuItemForm() {
        return "menuItems/addMenu";
    }

    @GetMapping("/edit/{id}")
    public String showEditMenuItemForm(@PathVariable Long id, Model model) {
        MenuItem menuItem = menuItemService.getMenuItemById(id);
        model.addAttribute("menuItem", menuItem);
        return "menuItems/editMenu";
    }
}
