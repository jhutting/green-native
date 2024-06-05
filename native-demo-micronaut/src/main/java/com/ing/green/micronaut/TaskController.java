package com.ing.green.micronaut;

import com.ing.green.micronaut.entity.TaskRepository;
import com.ing.green.micronaut.entity.TaskEntity;
import com.ing.green.micronaut.model.Task;
import com.ing.green.micronaut.model.TaskStatus;
import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.TypeConverter;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@ExecuteOn(TaskExecutors.BLOCKING)
@Controller("/tasks")
public class TaskController {
    protected final TaskRepository taskRepository;

    private Random random = new Random();

    public TaskController(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Get("")
    public List<Task> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(this::toTask)
                .toList();
    }

    @Get("/{id}")
    public Task getTaskById(Long id) {
        return toTask(taskRepository.findById(id).get());
    }

    @Get("/random/{max}")
    public Task getRandomTask(Long max) {
        Long taskId = random.nextLong(max) + 1;
        return toTask(taskRepository.findById(taskId).get());
    }

    private Task toTask(TaskEntity taskEntity) {
        return new Task(taskEntity.getId(), taskEntity.getTitle(), taskEntity.getAssignedTo(), TaskStatus.values()[taskEntity.getStatus()]);
    }
}
