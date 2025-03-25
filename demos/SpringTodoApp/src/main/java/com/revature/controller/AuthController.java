package com.revature.controller;

import com.revature.model.User;
import com.revature.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
//    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResponseEntity<User> register(@RequestBody User user) {
        User newUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
    // Login endpoint: Validate credentials and store the authenticated user in the session.
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User loginRequest, HttpSession session) {
        System.out.println("helloS");
        Optional<User> userOpt = userService.validateUser(loginRequest.getEmail(), loginRequest.getPassword());
        if (userOpt.isPresent()) {
            session.setAttribute("user", userOpt.get());
            System.out.println(userOpt.get().getEmail());
            return ResponseEntity.ok(userOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/session-check")public ResponseEntity<?> checkSession(HttpSession session) {
        User loggedUser = (User) session.getAttribute("user");
        if (loggedUser != null) {
            return ResponseEntity.ok(loggedUser); // Return user details if logged in
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session invalid or expired");
        }
    }


    // Logout endpoint: Invalidate the session to log the user out.
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out successfully");
    }

}
