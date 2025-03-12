package com.revature.service;

import com.revature.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<Task> findAllTasks();
    Optional<Task> findTaskById(Long id);
    Task createTask(Task task);
    Optional<Task> updateTask(Long id, Task task);
    boolean deleteTask(Long id);
}

