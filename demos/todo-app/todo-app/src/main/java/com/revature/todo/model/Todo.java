package com.revature.todo.model;

public class Todo {
    private int id;
    private int userId;
    private String title;
    private boolean isCompleted;

    public Todo() {}

    public Todo(int id, int userId, String title, boolean isCompleted) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.isCompleted = isCompleted;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }
}
