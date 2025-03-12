package com.revature.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "task_statuses")
public class TaskStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_status_id")
    private Long taskStatusId;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @OneToMany(mappedBy = "taskStatus")
    private List<Task> tasks = new ArrayList<>();

    // Getters and setters
    // ...
}

