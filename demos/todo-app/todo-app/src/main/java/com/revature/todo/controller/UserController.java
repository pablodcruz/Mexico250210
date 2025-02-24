package com.revature.todo.controller;

import com.revature.todo.dto.UserAuthRequestDTO;
import com.revature.todo.model.User;
import com.revature.todo.service.UserService;
import io.javalin.http.Context;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Handles POST /register with a JSON body:
     * {
     *    "username": "someName",
     *    "password": "somePass"
     * }
     */
    public void register(Context ctx) {
        // Parse request JSON into our DTO
        UserAuthRequestDTO req = ctx.bodyAsClass(UserAuthRequestDTO.class);

        if (req.getUsername() == null || req.getPassword() == null) {
            ctx.status(400).json("{\"error\":\"Missing username or password\"}");
            return;
        }

        boolean success = userService.registerUser(req.getUsername(), req.getPassword());
        if (success) {
            ctx.status(201).json("{\"message\":\"User registered successfully\"}");
        } else {
            ctx.status(409).json("{\"error\":\"Username already exists\"}");
        }
    }

    /**
     * Handles POST /login with a JSON body:
     * {
     *    "username": "someName",
     *    "password": "somePass"
     * }
     */
    public void login(Context ctx) {
        UserAuthRequestDTO user = ctx.bodyAsClass(UserAuthRequestDTO.class);

        if (user.getUsername() == null || user.getPassword() == null) {
            ctx.status(400).json("{\"error\":\"Missing username or password\"}");
            return;
        }

        boolean success = userService.loginUser(user.getUsername(), user.getPassword());
        if (success) {
            // Typically you might return a JWT or session token here
            // For now we will pretend they are authenticated without cookies
            ctx.status(200).json("{\"message\":\"Login successful\"}");
        } else {
            ctx.status(401).json("{\"error\":\"Invalid credentials\"}");
        }
    }

    /**
     * GET /users
     * Returns JSON of all users
     */
    public void getAllUsers(Context ctx) {
        List<User> users = userService.getAllUsers();
        ctx.json(users);
    }
}
