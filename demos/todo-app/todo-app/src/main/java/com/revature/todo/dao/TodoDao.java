package com.revature.todo.dao;

import com.revature.todo.model.Todo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoDao {
    private final String url;
    private final String dbUser;
    private final String dbPassword;

    public TodoDao(String url, String dbUser, String dbPassword) {
        this.url = url;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    /**
     * Create a new To-do, returning the same To-do object with the newly generated ID.
     */
    public Todo createTodo(Todo todo) {
        String sql = "INSERT INTO todos (user_id, title, is_completed) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, todo.getUserId());
            stmt.setString(2, todo.getTitle());
            stmt.setBoolean(3, todo.isCompleted());
            stmt.executeUpdate();

            // (Optional) Retrieve auto-generated ID.
            // Do this if you need to return the to-do id to the user for something else
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int newId = generatedKeys.getInt(1);
                    todo.setId(newId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todo;
    }

    /**
     * Fetch all Todos in the database.
     */
    public List<Todo> getAllTodos() {
        List<Todo> todos = new ArrayList<>();
        String sql = "SELECT * FROM todos";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Todo t = new Todo(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("title"),
                        rs.getBoolean("is_completed")
                );
                todos.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todos;
    }

    /**
     * Fetch all Todos belonging to a specific user (by user_id).
     */
    public List<Todo> getTodosByUserId(int userId) {
        List<Todo> todos = new ArrayList<>();
        String sql = "SELECT * FROM todos WHERE user_id = ?";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Todo t = new Todo(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getString("title"),
                            rs.getBoolean("is_completed")
                    );
                    todos.add(t);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todos;
    }

    /**
     * Update an existing To-do’s title/completion status by ID.
     */
    public void updateTodo(Todo todo) {
        String sql = "UPDATE todos SET title = ?, is_completed = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, todo.getTitle());
            stmt.setBoolean(2, todo.isCompleted());
            stmt.setInt(3, todo.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a specific To-do by ID.
     */
    public void deleteTodo(int todoId) {
        String sql = "DELETE FROM todos WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, todoId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
