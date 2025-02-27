package com.revature.todo.service;

import com.revature.todo.dao.UserDao;
import com.revature.todo.model.User;

import java.time.LocalDate;
import java.util.List;

public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }



    public User registerUser(String username, String rawPassword, LocalDate dateOfBirth) {
        // Typically, you'd hash this with BCrypt or Argon2
        String hashed = "HASHED_" + rawPassword;

        if (userDao.getUserByUsername(username) != null) {
            return null; // username exists
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPasswordHash(hashed);
        newUser.setDateOfBirth(dateOfBirth);

        return userDao.createUser(newUser);
    }

    public boolean loginUser(String username, String rawPassword) {
        User existingUser = userDao.getUserByUsername(username);
        if (existingUser == null) {
            return false; // user not found
        }

        // Compare hashed password
        String hashed = "HASHED_" + rawPassword;
        return hashed.equals(existingUser.getPasswordHash());
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
