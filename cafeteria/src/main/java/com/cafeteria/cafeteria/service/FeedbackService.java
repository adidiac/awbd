package com.cafeteria.cafeteria.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cafeteria.cafeteria.DbModels.Feedback;
import com.cafeteria.cafeteria.ViewModels.CreateFeedback;

public interface FeedbackService {

    List<Feedback> getAllFeedback();

    Feedback getFeedbackById(Long id);

    void createFeedback(CreateFeedback createFeedback);

    Feedback updateFeedback(Long id, Feedback feedback);

    void deleteFeedback(Long id);

    Page<Feedback> getAllFeedback(org.springframework.data.domain.Pageable pageable);
} 
