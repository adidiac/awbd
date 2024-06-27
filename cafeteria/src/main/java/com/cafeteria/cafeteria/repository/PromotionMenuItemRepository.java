package com.cafeteria.cafeteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafeteria.cafeteria.DbModels.PromotionMenuItem;

public interface PromotionMenuItemRepository extends JpaRepository<PromotionMenuItem, Long> {

    // Additional query methods if needed
    
}
