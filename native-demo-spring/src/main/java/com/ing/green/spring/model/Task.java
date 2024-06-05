package com.ing.green.spring.model;

public record Task(Long id, String title, String assignedTo, TaskStatus status) { }
