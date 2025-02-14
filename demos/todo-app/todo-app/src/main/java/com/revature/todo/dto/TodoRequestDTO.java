package com.revature.todo.dto;


/**
 * Simple DTO for creating/updating a to-do from JSON.
 * Example JSON:
 * {
 *   "userId": 1,
 *   "title": "My new task",
 *   "completed": false
 * }
 */
public class TodoRequestDTO {
    private int userId;
    private String title;
    private boolean completed;

    // Getters & setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}