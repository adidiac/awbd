package com.cafeteria.cafeteria.DbModels;

import jakarta.persistence.*;

@Entity
@Table(name = "promotion_menu_item")
public class PromotionMenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "promotion_id")
    private Long promotionId;

    @Column(name = "menu_item_id")
    private Long menuItemId;

    // constructors, getters, setters...
    public PromotionMenuItem() {
    }

    public PromotionMenuItem(Long id, Long promotionId, Long menuItemId) {
        this.id = id;
        this.promotionId = promotionId;
        this.menuItemId = menuItemId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }
}
