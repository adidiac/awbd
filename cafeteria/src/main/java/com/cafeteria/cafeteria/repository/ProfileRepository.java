package com.cafeteria.cafeteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafeteria.cafeteria.DbModels.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    // Additional query methods if needed
    
} 
