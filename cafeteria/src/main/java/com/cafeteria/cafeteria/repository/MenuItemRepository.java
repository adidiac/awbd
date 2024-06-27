package com.cafeteria.cafeteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafeteria.cafeteria.DbModels.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    // Additional query methods if needed
}


