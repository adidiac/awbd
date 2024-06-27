package com.cafeteria.cafeteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafeteria.cafeteria.DbModels.User;

public interface UserRepository extends JpaRepository<User, Long> {

    void findByUsername(String username);

    // Additional query methods if needed
    
}
