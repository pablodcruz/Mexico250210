package com.revature.service;

import com.revature.model.TaskCategory;
import org.springframework.stereotype.Service;
import com.revature.repository.TaskCategoryRepository;
import java.util.List;
import java.util.Optional;

@Service
public class TaskCategoryServiceImpl implements TaskCategoryService {

    private final TaskCategoryRepository taskCategoryRepository;

    public TaskCategoryServiceImpl(TaskCategoryRepository taskCategoryRepository) {
        this.taskCategoryRepository = taskCategoryRepository;
    }

    @Override
    public List<TaskCategory> findAllTaskCategories() {
        return taskCategoryRepository.findAll();
    }

    @Override
    public Optional<TaskCategory> findTaskCategoryById(Long id) {
        return taskCategoryRepository.findById(id);
    }

    @Override
    public TaskCategory createTaskCategory(TaskCategory category) {
        return taskCategoryRepository.save(category);
    }

    @Override
    public Optional<TaskCategory> updateTaskCategory(Long id, TaskCategory categoryDetails) {
        return taskCategoryRepository.findById(id).map(existingCategory -> {
            existingCategory.setName(categoryDetails.getName());
            return taskCategoryRepository.save(existingCategory);
        });
    }

    @Override
    public boolean deleteTaskCategory(Long id) {
        return taskCategoryRepository.findById(id).map(category -> {
            taskCategoryRepository.delete(category);
            return true;
        }).orElse(false);
    }
}

