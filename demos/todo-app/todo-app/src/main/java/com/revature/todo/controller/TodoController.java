package com.revature.todo.controller;

import com.revature.todo.dto.TodoRequestDTO;
import com.revature.todo.model.Todo;
import com.revature.todo.service.TodoService;
import io.javalin.http.Context;
import java.util.List;

public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    /**
     * POST /todos
     * JSON body:
     * {
     *   "userId": 1,
     *   "title": "Conquer the castle",
     *   "completed": false
     * }
     */
    public void createTodo(Context ctx) {
        TodoRequestDTO request = ctx.bodyAsClass(TodoRequestDTO.class);

        // Validate
        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            ctx.status(400).json("{\"error\":\"Missing todo title\"}");
            return;
        }

        // Construct a To-do model
        Todo todo = new Todo();
        todo.setUserId(request.getUserId());
        todo.setTitle(request.getTitle());
        todo.setCompleted(request.isCompleted());

        // Save via service
        todoService.addTodo(todo);

        // Return created item
        ctx.status(201).json(todo);
    }

    /**
     * GET /todos?userId=1
     *
     * If a 'userId' query param is present, returns only that user’s todos.
     * Otherwise, returns all todos.  (optional approach)
     */
    public void getTodos(Context ctx) {
        String userIdParam = ctx.queryParam("userId");
        if (userIdParam != null) {
            // Filter by user
            int userId = Integer.parseInt(userIdParam);
            List<Todo> userTodos = todoService.getUserTodos(userId);
            ctx.json(userTodos);
        } else {
            // Return all todos if no userId is specified
            List<Todo> allTodos = todoService.getAllTodos();
            ctx.json(allTodos);
        }
    }

    /**
     * PUT /todos/:id
     * JSON body:
     * {
     *   "title": "New Title",
     *   "completed": true
     * }
     */
    public void updateTodo(Context ctx) {
        int todoId = Integer.parseInt(ctx.pathParam("id"));
        TodoRequestDTO request = ctx.bodyAsClass(TodoRequestDTO.class);

        Todo todo = new Todo();
        todo.setId(todoId);
        todo.setTitle(request.getTitle());
        todo.setCompleted(request.isCompleted());

        todoService.updateTodo(todo);
        ctx.status(200).json("{\"message\":\"Todo updated\"}");
    }

    /**
     * DELETE /todos/:id
     */
    public void deleteTodo(Context ctx) {
        int todoId = Integer.parseInt(ctx.pathParam("id"));
        todoService.deleteTodo(todoId);
        ctx.status(200).json("{\"message\":\"Todo deleted\"}");
    }

    /**
     * GET /todos
     * Returns JSON array of ALL todos
     * (This can be used if you prefer a simpler dedicated endpoint.)
     */
    public void getAllTodos(Context ctx) {
        List<Todo> allTodos = todoService.getAllTodos();
        ctx.json(allTodos);
    }

    /**
     * GET /users/:id/todos
     * Returns JSON array of todos belonging to a specific user (by user ID)
     */
    public void getUserTodos(Context ctx) {
        int userId = Integer.parseInt(ctx.pathParam("id"));
        List<Todo> userTodos = todoService.getUserTodos(userId);
        ctx.json(userTodos);
    }
}
