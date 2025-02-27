package com.revature.todo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class User {
    private int id;
    private String username;
    private String passwordHash;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")  // ✅ Forces JSON format
    private LocalDate dateOfBirth;

    public User(String username, String passwordHash, LocalDate dateOfBirth) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.dateOfBirth = dateOfBirth;
    }

    public User() {

    }

    public User(int id, String username, String passwordHash, LocalDate dateOfBirth) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.dateOfBirth = dateOfBirth;
    }
    public User(int id, String username, String passwordHash) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public User(String username, String passwordHash){
        this.username = username;
        this.passwordHash = passwordHash;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }
}
