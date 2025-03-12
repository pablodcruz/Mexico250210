package com.revature.controller;

import com.revature.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping( value = "/register")
    public ResponseEntity<Users> register(@RequestBody Users newUsers) {
        // Logic to register a new user
        Users savedUsers = userService.saveUser(newUsers);
        return ResponseEntity.ok(savedUsers);
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Users login(@RequestBody Users loginRequest) {
        // Logic to authenticate user login
        return loginRequest;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Users> getUserProfile(@PathVariable Long userId) {
        // Logic to retrieve user profile
        return ResponseEntity.ok(new Users(userId, "Pablo", "password"));
    }
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        String message = "Hello, World!";
        return ResponseEntity.ok(message);
    }

    @GetMapping("/error")
    public ResponseEntity<String> error() {
        String errorMessage = "Internal Server Error";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorMessage);
    }

//    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
//    public @ResponseBody User updateUserProfile(@PathVariable Long userId, @RequestBody User updatedUser) {
//        // Logic to update user profile
//        return
//    }

    // Other handler methods for user operations...
}