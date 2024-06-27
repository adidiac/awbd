package com.cafeteria.cafeteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafeteria.cafeteria.DbModels.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Custom queries or methods if needed
}
