package com.revature.service;

import com.revature.model.TaskCategory;

import java.util.List;
import java.util.Optional;

public interface TaskCategoryService {
    List<TaskCategory> findAllTaskCategories();
    Optional<TaskCategory> findTaskCategoryById(Long id);
    TaskCategory createTaskCategory(TaskCategory category);
    Optional<TaskCategory> updateTaskCategory(Long id, TaskCategory category);
    boolean deleteTaskCategory(Long id);
}

