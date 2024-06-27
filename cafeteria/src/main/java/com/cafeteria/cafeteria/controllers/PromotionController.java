package com.cafeteria.cafeteria.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.cafeteria.Aspects.ValidateTokenAdmin;
import com.cafeteria.cafeteria.DbModels.Promotion;
import com.cafeteria.cafeteria.service.PromotionService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/promotion")
@Tag(name = "Promotion", description = "Promotion API for creating, getting, updating and deleting promotion entries")
public class PromotionController {
    
    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping
    public ResponseEntity<List<Promotion>> getAllPromotions() {
        List<Promotion> promotionEntries = promotionService.getAllPromotions();
        return ResponseEntity.ok(promotionEntries);
    }

    @PostMapping
    public ResponseEntity<Void> createPromotion(@RequestParam Promotion promotion) {
        promotionService.createPromotion(promotion);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updatePromotion(@RequestParam Promotion promotion) {
        promotionService.updatePromotion(promotion);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePromotion(@RequestParam Long id) {
        promotionService.deletePromotion(id);
        return ResponseEntity.ok().build();
    }

}
