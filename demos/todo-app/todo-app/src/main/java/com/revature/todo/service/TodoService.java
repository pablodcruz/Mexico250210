package com.revature.todo.service;

import com.revature.todo.dao.TodoDao;
import com.revature.todo.model.Todo;
import java.util.List;

public class TodoService {

    private final TodoDao todoDao;

    public TodoService(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    /**
     * Create/add a new To-do.
     */
    public void addTodo(Todo todo) {
        // Additional validation or business logic can go here
        todoDao.createTodo(todo);
    }

    /**
     * Retrieve all Todos.
     */
    public List<Todo> getAllTodos() {
        return todoDao.getAllTodos();
    }

    /**
     * Retrieve all Todos for a specific user.
     */
    public List<Todo> getUserTodos(int userId) {
        return todoDao.getTodosByUserId(userId);
    }

    /**
     * Update an existing To-do.
     */
    public void updateTodo(Todo todo) {
        todoDao.updateTodo(todo);
    }

    /**
     * Delete a To-do by ID.
     */
    public void deleteTodo(int todoId) {
        todoDao.deleteTodo(todoId);
    }
}
