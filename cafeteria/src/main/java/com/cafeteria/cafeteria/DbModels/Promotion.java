package com.cafeteria.cafeteria.DbModels;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Promotion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long promotion_id;
    private double discount;
    private String start_date;
    private String end_date;

    @ManyToMany
    @JoinTable(
        name = "promotion_menu_item",
        joinColumns = @JoinColumn(name = "promotion_id"),
        inverseJoinColumns = @JoinColumn(name = "menu_item_id"))
    private List<MenuItem> menuItems;

    public Promotion() {
    }

    public Promotion(Long promotion_id, double discount, String start_date, String end_date) {
        this.promotion_id = promotion_id;
        this.discount = discount;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public Long getPromotionId() {
        return this.promotion_id;
    }

    public void setPromotionId(Long promotion_id) {
        this.promotion_id = promotion_id;
    }

    public double getDiscount() {
        return this.discount;
    }

    public void setDiscount(double discount) {
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Discount must be between 0 and 100");
        }
        this.discount = discount;
    }

    public String getStartDate() {
        return this.start_date;
    }

    public void setStartDate(String start_date) {
        this.start_date = start_date;
    }

    public String getEndDate() {
        return this.end_date;
    }

    public void setEndDate(String end_date) {
        this.end_date = end_date;
    }

    @Override
    public String toString() {
        return "{" +
            " promotion_id='" + getPromotionId() + "'" +
            ", discount='" + getDiscount() + "'" +
            ", start_date='" + getStartDate() + "'" +
            ", end_date='" + getEndDate() + "'" +
            "}";
    }

    public List<MenuItem> getMenuItems() {
        return this.menuItems;
    }
}
