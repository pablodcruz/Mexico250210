package com.revature.demo.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Student {
    @Id
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses;
}

