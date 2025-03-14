package com.revature.controller;

import com.revature.model.User;
import com.revature.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Admin-only: List all users.
    @GetMapping
    public ResponseEntity<?> getAllUsers(HttpSession session) {
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in");
        }
        if (!"ADMIN".equals(sessionUser.getRole().getRoleName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
        }
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    // A user can get their own details or an admin can get any user's details.
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id, HttpSession session) {
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in");
        }
        // Allow if the session user is admin or the requested id matches the session user's id.
        if (!"ADMIN".equals(sessionUser.getRole().getRoleName()) && !sessionUser.getUserId().equals(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
        }
        return userService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Admin-only: Create a new user.
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user, HttpSession session) {
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in");
        }
        if (!"ADMIN".equals(sessionUser.getRole().getRoleName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
        }
        User savedUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    // A user can update their own details or an admin can update any user's details.
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User userDetails, HttpSession session) {
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in");
        }
        if (!"ADMIN".equals(sessionUser.getRole().getRoleName()) && !sessionUser.getUserId().equals(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
        }
        return userService.updateUser(id, userDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Admin-only: Delete a user.
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id, HttpSession session) {
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in");
        }
        if (!"ADMIN".equals(sessionUser.getRole().getRoleName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
        }
        boolean deleted = userService.deleteUser(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
