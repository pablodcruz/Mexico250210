package com.revature.todo.service;

import com.revature.todo.dao.UserDao;
import com.revature.todo.model.User;

import java.util.List;

public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean registerUser(String username, String rawPassword) {
        // Typically, you'd hash this with BCrypt or Argon2
        String hashed = "HASHED_" + rawPassword;

        if (userDao.getUserByUsername(username) != null) {
            return false; // username exists
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPasswordHash(hashed);
        userDao.createUser(newUser);
        return true;
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
