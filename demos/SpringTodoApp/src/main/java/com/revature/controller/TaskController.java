package com.revature.controller;

import com.revature.DTO.TaskResponseDto;
import com.revature.model.Task;
import com.revature.model.User;
import com.revature.service.MotivationalQuoteService;
import com.revature.service.TaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final MotivationalQuoteService motivationalQuoteService;

    public TaskController(TaskService taskService, MotivationalQuoteService motivationalQuoteService) {
        this.taskService = taskService;
        this.motivationalQuoteService = motivationalQuoteService;
    }

    /**
     * Returns all tasks.
     */
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.findAllTasks());
    }

    /**
     * Returns a specific task by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.findTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Creates a new task for the currently logged-in user and returns the saved task
     * along with a motivational quote.
     *
     * @param task The task to be created.
     * @param session The HttpSession containing the logged-in user.
     * @return A TaskResponseDto wrapping the saved task and a motivational quote.
     */
    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task task, HttpSession session) {
        // Retrieve the logged-in user from the session.
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) {
            // If no user is logged in, return 401 Unauthorized.
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in");
        }
        System.out.println(sessionUser.getEmail());
        // Associate the new task with the logged-in user's profile.
        task.setUserProfile(sessionUser.getUserProfile());
        // Save the task via the service layer.
        Task savedTask = taskService.createTask(task);
        // Retrieve a motivational quote from the external API.
        String quote = motivationalQuoteService.getRandomQuote();
        // Wrap the saved task and quote in a response DTO.
        TaskResponseDto responseDto = new TaskResponseDto(savedTask, quote);
        // Return the DTO with HTTP 201 Created.
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    /**
     * Updates an existing task.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        return taskService.updateTask(id, taskDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Deletes a task.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        boolean deleted = taskService.deleteTask(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
