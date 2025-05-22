package com.ing.green.quarkus.model;

public record Task(
        Long id,
        String title,
        String assignedTo,
        TaskStatus status) { }
