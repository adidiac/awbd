package com.cafeteria.cafeteria.service;

import java.util.HashMap;
import java.util.List;

public interface AnalyticsService {

    public HashMap<String,Integer> getMostPopularItems();

    public List<String> getMostProfitableDates();
}