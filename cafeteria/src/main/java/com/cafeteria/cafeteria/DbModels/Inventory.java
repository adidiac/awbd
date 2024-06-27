package com.cafeteria.cafeteria.DbModels;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Long inventory_id;
    private String item_name;
    private int quantity;

    public Inventory() {
    }

    public Inventory(Long inventory_id, String item_name, int quantity) {
        this.inventory_id = inventory_id;
        this.item_name = item_name;
        this.quantity = quantity;
    }

    // write everything needed
    public Long getInventoryId() {
        return this.inventory_id;
    }

    public void setInventoryId(Long inventory_id) {
        this.inventory_id = inventory_id;
    }

    public String getItemName() {
        return this.item_name;
    }

    public void setItemName(String item_name) {
        this.item_name = item_name;
    }
    
    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
