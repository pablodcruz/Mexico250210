package com.revature.service;

import com.revature.model.TaskStatus;
import com.revature.repository.TaskStatusRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaskStatusServiceImpl implements TaskStatusService {

    private final TaskStatusRepository taskStatusRepository;

    public TaskStatusServiceImpl(TaskStatusRepository taskStatusRepository) {
        this.taskStatusRepository = taskStatusRepository;
    }

    @Override
    public List<TaskStatus> findAllTaskStatuses() {
        return taskStatusRepository.findAll();
    }

    @Override
    public Optional<TaskStatus> findTaskStatusById(Long id) {
        return taskStatusRepository.findById(id);
    }

    @Override
    public TaskStatus createTaskStatus(TaskStatus status) {
        return taskStatusRepository.save(status);
    }

    @Override
    public Optional<TaskStatus> updateTaskStatus(Long id, TaskStatus statusDetails) {
        return taskStatusRepository.findById(id).map(existingStatus -> {
            existingStatus.setStatus(statusDetails.getStatus());
            return taskStatusRepository.save(existingStatus);
        });
    }

    @Override
    public boolean deleteTaskStatus(Long id) {
        return taskStatusRepository.findById(id).map(status -> {
            taskStatusRepository.delete(status);
            return true;
        }).orElse(false);
    }
}

