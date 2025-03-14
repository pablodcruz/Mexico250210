package com.revature.DTO;

import com.revature.model.Task;

public class TaskResponseDto {
    private Task task;
    private String motivationalQuote;

    public TaskResponseDto(Task task, String motivationalQuote) {
        this.task = task;
        this.motivationalQuote = motivationalQuote;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getMotivationalQuote() {
        return motivationalQuote;
    }

    public void setMotivationalQuote(String motivationalQuote) {
        this.motivationalQuote = motivationalQuote;
    }
}
