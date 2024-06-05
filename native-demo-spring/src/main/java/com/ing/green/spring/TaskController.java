package com.ing.green.spring;

import com.ing.green.spring.entity.TaskEntity;
import com.ing.green.spring.entity.TaskRepository;
import com.ing.green.spring.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    private Random rng = new Random();

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Task> getTasks() {
        return taskRepository.findAllAsRecord();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Task getTaskById(@PathVariable("id") Long taskId) {
        return taskRepository.findTaskById(taskId);
    }

    @GetMapping(value = "/random/{max}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Task getRandomTask(@PathVariable("max") Long max) {
        Long taskId = rng.nextLong(max) + 1;
        return taskRepository.findTaskById(taskId);
    }

    @GetMapping(value = "/random-convert/{max}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Task getRandomTaskWithConversion(@PathVariable("max") Long max) {
        Long taskId = rng.nextLong(max) + 1;
        return toTask(taskRepository.findById(taskId).get());
    }

    private Task toTask(TaskEntity taskEntity) {
        return new Task(taskEntity.getId(), taskEntity.getTitle(), taskEntity.getAssignedTo(), taskEntity.getStatus());
    }
}
