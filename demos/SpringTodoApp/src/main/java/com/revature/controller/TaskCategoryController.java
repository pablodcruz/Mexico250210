package com.revature.controller;

import com.revature.model.TaskCategory;
import com.revature.repository.TaskCategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task-categories")
public class TaskCategoryController {

    private final TaskCategoryRepository taskCategoryRepository;

    public TaskCategoryController(TaskCategoryRepository taskCategoryRepository) {
        this.taskCategoryRepository = taskCategoryRepository;
    }

    // GET all task categories
    @GetMapping
    public ResponseEntity<List<TaskCategory>> getAllTaskCategories() {
        return ResponseEntity.ok(taskCategoryRepository.findAll());
    }

    // GET task category by id
    @GetMapping("/{id}")
    public ResponseEntity<TaskCategory> getTaskCategoryById(@PathVariable Long id) {
        return taskCategoryRepository.findById(id)
                .map(category -> ResponseEntity.ok(category))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST create new task category
    @PostMapping
    public ResponseEntity<TaskCategory> createTaskCategory(@RequestBody TaskCategory category) {
        TaskCategory savedCategory = taskCategoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    // PUT update task category
    @PutMapping("/{id}")
    public ResponseEntity<TaskCategory> updateTaskCategory(@PathVariable Long id, @RequestBody TaskCategory categoryDetails) {
        return taskCategoryRepository.findById(id)
                .map(existingCategory -> {
                    existingCategory.setName(categoryDetails.getName());
                    TaskCategory updatedCategory = taskCategoryRepository.save(existingCategory);
                    return ResponseEntity.ok(updatedCategory);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}

