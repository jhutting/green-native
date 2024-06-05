package com.ing.green.micronaut.model;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record Task(Long id, String title, String assignedTo, TaskStatus status) { }
