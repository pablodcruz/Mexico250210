package com.revature.controller;


import com.revature.model.TaskStatus;
import com.revature.service.TaskStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/task-statuses")
public class TaskStatusController {

    private final TaskStatusService taskStatusService;

    public TaskStatusController(TaskStatusService taskStatusService) {
        this.taskStatusService = taskStatusService;
    }

    @GetMapping
    public ResponseEntity<List<TaskStatus>> getAllTaskStatuses() {
        return ResponseEntity.ok(taskStatusService.findAllTaskStatuses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskStatus> getTaskStatusById(@PathVariable Long id) {
        return taskStatusService.findTaskStatusById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TaskStatus> createTaskStatus(@RequestBody TaskStatus status) {
        TaskStatus savedStatus = taskStatusService.createTaskStatus(status);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStatus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskStatus> updateTaskStatus(@PathVariable Long id, @RequestBody TaskStatus statusDetails) {
        return taskStatusService.updateTaskStatus(id, statusDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskStatus(@PathVariable Long id) {
        boolean deleted = taskStatusService.deleteTaskStatus(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
