package com.cafeteria.cafeteria.DbModels;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "UserTable")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long user_id;
    private String username;
    private String password;
    private String full_name;
    private String email;
    private String role;
    
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Profile profile;

    public User() {
    }

    public User(Long user_id, String username, String password, String full_name, String email, String role) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.full_name = full_name;
        this.email = email;
        this.role = role;
    }
    // write everything needed
    public Long getUserId() {
        return this.user_id;
    }

    public void setUserId(Long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        if (username.length() > 50) {
            throw new IllegalArgumentException("Username cannot be longer than 50 characters");
        }
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        if (password.length() > 255) {
            throw new IllegalArgumentException("Password cannot be longer than 255 characters");
        }
        this.password = password;
    }

    public String getFullName() {
        return this.full_name;
    }

    public void setFullName(String full_name) {
        if (full_name.length() > 100) {
            throw new IllegalArgumentException("Full name cannot be longer than 100 characters");
        }
        this.full_name = full_name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        if (email.length() > 100) {
            throw new IllegalArgumentException("Email cannot be longer than 100 characters");
        }
        this.email = email;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        if (role.length() > 20) {
            throw new IllegalArgumentException("Role cannot be longer than 20 characters");
        }
        this.role = role;
    }

    @Override
    public String toString() {
        return "{" +
            " user_id='" + getUserId() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", full_name='" + getFullName() + "'" +
            ", email='" + getEmail() + "'" +
            ", role='" + getRole() + "'" +
            "}";
    }

    public List<Order> getOrders() {
        return this.orders;
    }
}
