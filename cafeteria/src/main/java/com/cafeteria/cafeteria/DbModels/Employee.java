package com.cafeteria.cafeteria.DbModels;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    private String firstName;
    private String lastName;

    private String username;

    @Column(name = "full_name")
    private String fullName;
    private String email;
    private String password;
    private String role;

    @Column(name = "contact_number")
    private String contactNumber;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String username, String email, String password, String role,
            String contactNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.fullName = firstName + " " + lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.contactNumber = contactNumber;
    }

    // getters and setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee id(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.length() > 50) {
            throw new IllegalArgumentException("First name cannot be longer than 50 characters");
        }
        this.firstName = firstName;
    }

    public Employee firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.length() > 50) {
            throw new IllegalArgumentException("Last name cannot be longer than 50 characters");
        }
        this.lastName = lastName;
    }

    public Employee lastName(String lastName) {
        this.lastName = lastName;
        return this;
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

    public Employee username(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        if (fullName.length() > 100) {
            throw new IllegalArgumentException("Full name cannot be longer than 100 characters");
        }
        this.fullName = fullName;
    }

    public Employee fullName(String fullName) {
        this.fullName = fullName;
        return this;
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

    public Employee email(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        if (password.length() > 100) {
            throw new IllegalArgumentException("Password cannot be longer than 100 characters");
        }
        this.password = password;
    }

    public Employee password(String password) {
        this.password = password;
        return this;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        if (role.length() > 50) {
            throw new IllegalArgumentException("Role cannot be longer than 50 characters");
        }
        this.role = role;
    }

    public Employee role(String role) {
        this.role = role;
        return this;
    }

    public String getContactNumber() {
        return this.contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        if (contactNumber.length() > 50) {
            throw new IllegalArgumentException("Contact number cannot be longer than 50 characters");
        }
        this.contactNumber = contactNumber;
    }

    public Employee contactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
        return this;
    }
}

