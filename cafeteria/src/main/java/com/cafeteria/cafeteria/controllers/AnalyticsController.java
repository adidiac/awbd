package com.cafeteria.cafeteria.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.cafeteria.service.AnalyticsService;

import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/analytics")
@Tag(name = "Analytics", description = "Analytics API for see the most popular items and most profitable dates")
public class AnalyticsController {
    
    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/most-popular-items")
    public ResponseEntity<HashMap<String,Integer>> getMostPopularItems() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            var mostPopularItems = analyticsService.getMostPopularItems();
            return ResponseEntity.ok(mostPopularItems);
        }
        return ResponseEntity.status(401).build();
    }

    @GetMapping("/most-profitable-dates")
    public ResponseEntity<List<String>> getMostProfitableDates() {
        // Just to prove the JDBC Security is working
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            var mostProfitableDates = analyticsService.getMostProfitableDates();
            return ResponseEntity.ok(mostProfitableDates);
        }
        return ResponseEntity.status(401).build();
    }
}
