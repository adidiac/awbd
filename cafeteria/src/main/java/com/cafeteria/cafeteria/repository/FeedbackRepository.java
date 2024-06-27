package com.cafeteria.cafeteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafeteria.cafeteria.DbModels.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    // Custom queries or methods if needed
}
