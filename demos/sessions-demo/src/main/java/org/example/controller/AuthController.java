package org.example.controller;

import io.javalin.http.Context;
import jakarta.servlet.http.HttpSession;
import org.example.model.User;

import java.sql.*;

public class AuthController {

    /**
     * POST /register
     * JSON body:
     * {
     *   "username": "alice",
     *   "password": "secret"
     * }
     */
    public static void register(Context ctx) {
        User requestUser = ctx.bodyAsClass(User.class);

        // Basic validation
        if (requestUser.getUsername() == null || requestUser.getPassword() == null) {
            ctx.status(400).json("{\"error\":\"Missing username or password\"}");
            return;
        }

        // Check if user already exists
        if (userExists(requestUser.getUsername())) {
            ctx.status(409).json("{\"error\":\"Username already taken\"}");
            return;
        }

        // Insert new user
        boolean created = createUserInDB(requestUser);
        if (created) {
            ctx.status(201).json(requestUser);
        } else {
            ctx.status(500).json("{\"error\":\"Failed to register user\"}");
        }
    }

    /**
     * POST /login
     * JSON body:
     * {
     *   "username": "alice",
     *   "password": "secret"
     * }
     */
    public static void login(Context ctx) {
        User requestUser = ctx.bodyAsClass(User.class);
        if (requestUser.getUsername() == null || requestUser.getPassword() == null) {
            ctx.status(400).json("{\"error\":\"Missing username or password\"}");
        }

        // Check credentials. dbUser makes it clear we got this data from the db after verifying with the requestUser.
        User dbUser = getUserFromDB(requestUser.getUsername());
        if (dbUser == null) {
            ctx.status(401).json("{\"error\":\"Invalid credentials\"}");
            return;
        }

        // Compare password (plain text for demo)
        if (!dbUser.getPassword().equals(requestUser.getPassword())) {
            ctx.status(401).json("{\"error\":\"Invalid credentials\"}");
            return;
        }

        // If valid, start a session
        HttpSession session = ctx.req().getSession(true);
        session.setAttribute("user", dbUser);
        session.

        ctx.status(200).json(dbUser);
    }

    /**
     * GET /check
     * Check if user is logged in
     */
    public static void checkLogin(Context ctx) {
        HttpSession session = ctx.req().getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            ctx.status(200).json(user);
        } else {
            ctx.status(401).json("{\"error\":\"Not logged in\"}");
        }

    }

    /**
     * POST /logout
     */
    public static void logout(Context ctx) {
        HttpSession session = ctx.req().getSession();
        if (session != null) {
            session.invalidate();
        }
        ctx.status(200).json("{\"message\":\"Logged out\"}");
    }

    // -----------------------------------------------------------------------
    // H2 Database Methods, No DAO layer for simplicity
    // -----------------------------------------------------------------------

    private static final String DB_URL = "jdbc:h2:mem:demo;DB_CLOSE_DELAY=-1"; // in-memory DB
    private static final String USER = "sa";
    private static final String PASS = "";  // default for H2 in memory

    /**
     * Checks if a username already exists.
     */
    private static boolean userExists(String username) {
        String sql = "SELECT 1 FROM users WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();  // if there's a row, user exists
        } catch (SQLException e) {
            e.printStackTrace();
            return true; // fail safe, assume it exists if error
        }
    }

    /**
     * Creates a new user in the DB, returns true if successful.
     */
    private static boolean createUserInDB(User user) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves a user by username or returns null if not found.
     */
    private static User getUserFromDB(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return user;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
