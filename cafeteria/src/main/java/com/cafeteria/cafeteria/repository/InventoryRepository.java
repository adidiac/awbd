package com.cafeteria.cafeteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafeteria.cafeteria.DbModels.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    // Custom queries or methods if needed
}
