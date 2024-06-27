package com.cafeteria.cafeteria.service.Impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cafeteria.cafeteria.DbModels.Promotion;
import com.cafeteria.cafeteria.repository.PromotionRepository;
import com.cafeteria.cafeteria.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;

    public PromotionServiceImpl(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    @Override
    public Promotion createPromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    @Override
    public Promotion updatePromotion(Promotion promotion) {
        var promotionToUpdate = promotionRepository.findById(promotion.getPromotionId()).orElseThrow();
        promotionToUpdate.setDiscount(promotion.getDiscount());
        promotionToUpdate.setEndDate(promotion.getEndDate());
        promotionToUpdate.setStartDate(promotion.getStartDate());
        return promotionRepository.save(promotionToUpdate);
    }

    @Override
    public void deletePromotion(Long id) {
        promotionRepository.deleteById(id);
    }

    @Override
    public Promotion getPromotionById(Long id) {
        return promotionRepository.findById(id).orElseThrow();
    }

    @Override
    public Double calculatePromotionPrice(Double price, Long menuItemId) {
        var promotions = promotionRepository.findAll();
        for (Promotion promotion : promotions) {
            if (promotion.getMenuItems().stream().anyMatch(menuItem -> menuItem.getId() == menuItemId)) {
                LocalDate todayDate = LocalDate.now();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                var promotionStartDate = LocalDate.parse(promotion.getStartDate(), formatter);
                var promotionEndDate = LocalDate.parse(promotion.getEndDate(), formatter);

                if (todayDate.isAfter(promotionStartDate) && todayDate.isBefore(promotionEndDate)) {
                    return price - (price * promotion.getDiscount() / 100);
                }

                return price;
            }
        }
        return price;
    }

    @Override
    public void deletePromotionByMenuItemId(Long menuItemId) {
        var promotions = promotionRepository.findAll();
        for (Promotion promotion : promotions) {
            if (promotion.getMenuItems().stream().anyMatch(menuItem -> menuItem.getId() == menuItemId)) {
                promotionRepository.deleteById(promotion.getPromotionId());
            }
        }
    }
}
