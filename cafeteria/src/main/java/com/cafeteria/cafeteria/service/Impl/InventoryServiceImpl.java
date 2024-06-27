package com.cafeteria.cafeteria.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cafeteria.cafeteria.DbModels.Inventory;
import com.cafeteria.cafeteria.repository.InventoryRepository;
import com.cafeteria.cafeteria.service.InventoryService;
@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory getInventoryById(Long id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    @Override
    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory updateInventory(Inventory inventory) {
        
        Inventory existingInventory = inventoryRepository.findById(inventory.getInventoryId()).orElse(null);
        existingInventory.setQuantity(inventory.getQuantity());
        existingInventory.setItemName(inventory.getItemName());
        return inventoryRepository.save(existingInventory);
    }

    @Override
    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }
    
}
