package com.cafeteria.cafeteria.service;

import java.util.List;

import com.cafeteria.cafeteria.DbModels.Promotion;

public interface PromotionService {
    
    List<Promotion> getAllPromotions();
    
    Promotion createPromotion(Promotion promotion);

    Promotion updatePromotion(Promotion promotion);

    void deletePromotion(Long id);

    Promotion getPromotionById(Long id);

    Double calculatePromotionPrice(Double price, Long menuItemId);

    void deletePromotionByMenuItemId(Long menuItemId);
}
