package com.revature.todo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

/**
 * Simple DTO for registration/login JSON requests.
 * Example JSON:
 * {
 *   "username": "someUser",
 *   "password": "somePass"
 * }
 */
public class UserAuthRequestDTO {
    private String username;
    private String password;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")  // ✅ Forces JSON format
    private LocalDate dateOfBirth;  // Maps to PostgreSQL DATE type

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    // Getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
