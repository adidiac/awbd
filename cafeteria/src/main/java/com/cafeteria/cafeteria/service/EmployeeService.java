package com.cafeteria.cafeteria.service;
import java.util.List;

import com.cafeteria.cafeteria.DbModels.Employee;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Long id, Employee employee);

    void deleteEmployee(Long id);
}