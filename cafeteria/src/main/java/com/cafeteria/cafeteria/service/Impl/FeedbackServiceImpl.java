package com.cafeteria.cafeteria.service.Impl;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.cafeteria.cafeteria.DbModels.Feedback;
import com.cafeteria.cafeteria.ViewModels.CreateFeedback;
import com.cafeteria.cafeteria.repository.FeedbackRepository;
import com.cafeteria.cafeteria.service.FeedbackService;
import com.cafeteria.cafeteria.service.UserService;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    
    private final FeedbackRepository feedbackRepository;
    private final UserService userService;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, UserService userService) {
        this.feedbackRepository = feedbackRepository;
        this.userService = userService;
    }

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id).orElseThrow();
    }

    @Override
    public void createFeedback(CreateFeedback feedbackEntry) {
        Feedback newFeedback = new Feedback();
        Long userId = userService.getUserIdByUsername(feedbackEntry.username);
        newFeedback.setUserId(userId);
        newFeedback.setFeedbackText(feedbackEntry.feedback);
        newFeedback.setDateFromLocalDate();
        newFeedback.setRating(feedbackEntry.rating);
        feedbackRepository.save(newFeedback);
    }

    @Override
    public Feedback updateFeedback(Long id, Feedback feedback) {
        return feedbackRepository.findById(id).map(feedbackEntry -> {
            feedbackEntry.setUserId(feedback.getUserId());
            feedbackEntry.setFeedbackText(feedback.getFeedbackText());
            feedbackEntry.setFeedbackDate(feedback.getFeedbackDate());
            feedbackEntry.setRating(feedback.getRating());
            return feedbackRepository.save(feedbackEntry);
        }).orElseThrow();
    }

    @Override
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }

    @Override
    public Page<Feedback> getAllFeedback(org.springframework.data.domain.Pageable pageable) {
        return feedbackRepository.findAll(pageable);
    }

}
