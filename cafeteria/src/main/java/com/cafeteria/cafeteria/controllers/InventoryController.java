package com.cafeteria.cafeteria.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.cafeteria.Aspects.ValidateTokenAdmin;
import com.cafeteria.cafeteria.DbModels.Inventory;
import com.cafeteria.cafeteria.service.InventoryService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/inventory")
@Tag(name = "Inventory", description = "Inventory API for creating, getting, updating and deleting inventory entries")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    @ValidateTokenAdmin
    public ResponseEntity<List<Inventory>> getAllInventory() {
        List<Inventory> inventoryEntries = inventoryService.getAllInventory();
        return ResponseEntity.ok(inventoryEntries);
    }

    @PostMapping
    @ValidateTokenAdmin
    public ResponseEntity<Void> createInventory(@RequestParam Inventory inventory) {
        inventoryService.createInventory(inventory);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @ValidateTokenAdmin
    public ResponseEntity<Void> updateInventory(@PathVariable Long id, @RequestParam Inventory inventory) {
        inventoryService.updateInventory(inventory);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ValidateTokenAdmin
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        try {
            inventoryService.deleteInventory(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
