package com.revature.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.todo.controller.TodoController;
import com.revature.todo.controller.UserController;
import com.revature.todo.dao.TodoDao;
import com.revature.todo.dao.UserDao;
import com.revature.todo.service.TodoService;
import com.revature.todo.service.UserService;
import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class Main {

    // 1) (Optional) DROP TABLES (to reset each time, ensuring a clean state for testing)
    private static final String DROP_TABLES_SQL = """
        DROP TABLE IF EXISTS todos CASCADE;
        DROP TABLE IF EXISTS users CASCADE;
        """;

    // 2) CREATE TABLES
    private static final String CREATE_TABLES_SQL = """
        CREATE TABLE IF NOT EXISTS users (
            id SERIAL PRIMARY KEY,
            username VARCHAR(50) NOT NULL UNIQUE,
            password_hash VARCHAR(255) NOT NULL,
            date_of_birth DATE NOT NULL
        );

        CREATE TABLE IF NOT EXISTS todos (
            id SERIAL PRIMARY KEY,
            user_id INT NOT NULL,
            title VARCHAR(100) NOT NULL,
            is_completed BOOLEAN DEFAULT FALSE,
            FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
        );
        """;

    // 3) INSERT SAMPLE DATA
    private static final String INSERT_DATA_SQL = """
        -- Insert sample users
        INSERT INTO users (username, password_hash, date_of_birth)
        VALUES
            ('knight_arthur', 'HASHED_excalibur', '01/22/1999'),
            ('lady_gwen', 'HASHED_gauntlet', '01/22/1998');

        -- Insert sample todos
        INSERT INTO todos (user_id, title, is_completed)
        VALUES
            (1, 'Slay the dragon', false),
            (1, 'Polish armor', false),
            (2, 'Prepare feast', false);
        """;

    public static void main(String[] args) {
        // Database credentials
        String jdbcUrl = "jdbc:postgresql://localhost:5432/todo_db";
        String dbUser = "postgres";
        String dbPassword = "final2kk";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());  // 🔥 Enables LocalDate parsing


        // 4) Initialize DB
        resetDatabase(jdbcUrl, dbUser, dbPassword);

        // 5) Create DAOs, Services, Controllers
        UserDao userDao = new UserDao(jdbcUrl, dbUser, dbPassword);
        TodoDao todoDao = new TodoDao(jdbcUrl, dbUser, dbPassword);

        UserService userService = new UserService(userDao);
        TodoService todoService = new TodoService(todoDao);

        UserController userController = new UserController(userService);
        TodoController todoController = new TodoController(todoService);

        // 6) Start Javalin (we will use Javalin version 4+)
        Javalin app = Javalin.create(config -> {
            // If needed, you can configure plugins, CORS, etc. here.
            // For example: config.plugins.enableCors(cors -> cors.add(anyOriginAllowed));
            // This is needed when working with web app on a browser.

            //support for LocalDate through Jackson
            config.jsonMapper(new JavalinJackson(objectMapper)); // ✅ Register the custom JSON mapper

        }).start(7000);

        // 7) Define routes using the new {param} syntax
        app.post("/register", userController::register);
        app.post("/login", userController::login);
        app.get("/users", userController::getAllUsers);

        app.get("/users/{id}/todos", todoController::getUserTodos);

        app.post("/todos", todoController::createTodo);
        app.put("/todos/{id}", todoController::updateTodo);
        app.delete("/todos/{id}", todoController::deleteTodo);
        app.get("/todos", todoController::getAllTodos);

        System.out.println("Server running on http://localhost:7000/");

        List<String> names = Arrays.asList("Charlie", "Alice", "Bob");
        names.sort((a, b) -> a.compareTo(b));
        System.out.println(names); // [Alice, Bob, Charlie]
    }

    // Reset the DB to a clean state each time
    private static void resetDatabase(String jdbcUrl, String dbUser, String dbPassword) {
        runSql(DROP_TABLES_SQL, jdbcUrl, dbUser, dbPassword);
        runSql(CREATE_TABLES_SQL, jdbcUrl, dbUser, dbPassword);
        runSql(INSERT_DATA_SQL, jdbcUrl, dbUser, dbPassword);
    }

    private static void runSql(String sql, String jdbcUrl, String dbUser, String dbPassword) {
        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Executed SQL:\n" + sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
