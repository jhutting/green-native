package com.ing.green.spring.entity;

import com.ing.green.spring.model.TaskStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(name = "assignedto") // as we created the table with Panache, we'll have to override the spring-data assigned_to value
    private String assignedTo;
    private TaskStatus status;

    public String getTitle() {
        return title;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public Long getId() {
        return id;
    }
}
