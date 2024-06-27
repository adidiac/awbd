package com.cafeteria.cafeteria.DbModels;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Long id;

    private Long user_id;
    private String feedback_text;
    private String feedback_date;
    private int rating;


    // Constructors, getters, setters
    public Feedback() {
    }

    public Feedback(Long user_id, String feedback_text, String feedback_date, int rating) {
        this.user_id = user_id;
        this.feedback_text = feedback_text;
        this.feedback_date = feedback_date;
        this.rating = rating;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long feedback_id) {
        this.id = feedback_id;
    }

    public Long getUserId() {
        return this.user_id;
    }

    public void setUserId(Long user_id) {
        this.user_id = user_id;
    }

    public String getFeedbackText() {
        return this.feedback_text;
    }

    public void setFeedbackText(String feedback_text) {
        if (feedback_text.length() > 255) {
            throw new IllegalArgumentException("Feedback text cannot be longer than 255 characters");
        }
        this.feedback_text = feedback_text;
    }

    public String getFeedbackDate() {
        return this.feedback_date;
    }

    public void setFeedbackDate(String feedback_date) {
        this.feedback_date = feedback_date;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", user_id='" + getUserId() + "'" +
            ", feedback_text='" + getFeedbackText() + "'" +
            ", feedback_date='" + getFeedbackDate() + "'" +
            ", rating='" + getRating() + "'" +
            "}";
    }
    
    public void setDateFromLocalDate() {
        java.time.LocalDate date = java.time.LocalDate.now();
        this.feedback_date = date.toString();
    }
}
