package com.cafeteria.cafeteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafeteria.cafeteria.DbModels.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    // Custom queries or methods if needed
}
