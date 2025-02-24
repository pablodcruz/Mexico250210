package org.example;

import io.javalin.Javalin;
import org.example.controller.AuthController;

import java.sql.*;
public class App {
    public static void main(String[] args) {
        // 1. Initialize H2 in-memory database
        initH2();

        // 2. Start Javalin
        Javalin app = Javalin.create(config -> {
//            config.enableCorsForAllOrigins();
        }).start(7000);

        // 3. Map endpoints
        // POST /register
        app.post("/register", AuthController::register);

        // POST /login
        app.post("/login", AuthController::login);

        // GET /check
        app.get("/check", AuthController::checkLogin);

        // POST /logout
        app.post("/logout", AuthController::logout);

        System.out.println("Server running on http://localhost:7000");
    }

    private static void initH2() {
        String DB_URL = "jdbc:h2:mem:demo;DB_CLOSE_DELAY=-1";
        String USER = "sa";
        String PASS = "";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            // Create a simple USERS table
            // user_id is auto-increment
            String sql = "CREATE TABLE IF NOT EXISTS users ("
                    + " user_id INT AUTO_INCREMENT PRIMARY KEY,"
                    + " username VARCHAR(50) UNIQUE NOT NULL,"
                    + " password VARCHAR(100) NOT NULL"
                    + ");";
            stmt.execute(sql);
            System.out.println("H2 in-memory database initialized");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
