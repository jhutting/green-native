package com.ing.green.spring.entity;

import com.ing.green.spring.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    @Query("SELECT new com.ing.green.spring.model.Task(t.id, t.title, t.assignedTo, t.status) FROM TaskEntity t")
    List<Task> findAllAsRecord();

    @Query("SELECT new com.ing.green.spring.model.Task(t.id, t.title, t.assignedTo, t.status) FROM TaskEntity t WHERE t.id = :id")
    Task findTaskById(Long id);
}
