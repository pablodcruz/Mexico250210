package com.revature.service;

import com.revature.model.TaskStatus;

import java.util.List;
import java.util.Optional;

public interface TaskStatusService {
    List<TaskStatus> findAllTaskStatuses();
    Optional<TaskStatus> findTaskStatusById(Long id);
    TaskStatus createTaskStatus(TaskStatus status);
    Optional<TaskStatus> updateTaskStatus(Long id, TaskStatus status);
    boolean deleteTaskStatus(Long id);
}

