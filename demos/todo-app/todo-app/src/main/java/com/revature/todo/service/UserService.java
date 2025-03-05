package com.revature.todo.service;

import com.revature.todo.dao.UserDao;
import com.revature.todo.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDate;
import java.util.List;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Registers a new user by hashing the password before storing it.
     * @param username The username.
     * @param rawPassword The plain-text password.
     * @param dateOfBirth The user's date of birth.
     * @return The created User object or null if username already exists.
     */
    public User registerUser(String username, String rawPassword, LocalDate dateOfBirth) {
        if (userDao.getUserByUsername(username) != null) {
            return null; // Username already exists
        }

        String hashedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt(12)); // Hash password

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPasswordHash(hashedPassword);
        newUser.setDateOfBirth(dateOfBirth);

        return userDao.createUser(newUser);
    }

    /**
     * Logs in a user by verifying the hashed password.
     * @param username The username.
     * @param rawPassword The plain-text password entered by the user.
     * @return true if login is successful, false otherwise.
     */
    public boolean loginUser(String username, String rawPassword) {
        User existingUser = userDao.getUserByUsername(username);
        if (existingUser == null) {
            return false; // User not found
        }

        return BCrypt.checkpw(rawPassword, existingUser.getPasswordHash()); // Verify password
    }

    /**
     * Retrieves all users from the database.
     * @return List of users.
     */
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
