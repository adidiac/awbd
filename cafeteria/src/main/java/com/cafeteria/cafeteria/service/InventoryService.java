package com.cafeteria.cafeteria.service;

import java.util.List;

import com.cafeteria.cafeteria.DbModels.Inventory;

public interface InventoryService {
    
    List<Inventory> getAllInventory();
    Inventory getInventoryById(Long id);
    Inventory createInventory(Inventory inventory);
    Inventory updateInventory(Inventory inventory);
    void deleteInventory(Long id);
} 