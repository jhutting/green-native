package com.ing.green.quarkus;

import com.ing.green.quarkus.entity.TaskEntity;
import com.ing.green.quarkus.model.Task;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;

import java.util.List;
import java.util.Random;

@Path("/tasks")
public class TaskResource {
    private Random random = new Random();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public List<Task> getAllTasks() {
        return TaskEntity.listAll()
                .stream()
                .map(te -> toTask((TaskEntity) te))
                .toList();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Task getSpecificTask(@PathParam("id") Long id) {
        TaskEntity taskEntity = TaskEntity.findById(id);
        if (taskEntity == null) {
            throw new WebApplicationException("Task with id of " + id + " does not exist.", 404);
        }
        return toTask(taskEntity);
    }

    @GET
    @Path("random/{max}")
    @Produces(MediaType.TEXT_PLAIN)
    public Task getRandomTask(@PathParam("max") Long max) {
        Long id = random.nextLong(max) + 1;
        TaskEntity taskEntity = TaskEntity.findById(id);
        if (taskEntity == null) {
            throw new WebApplicationException("Task with id of " + id + " does not exist.", 404);
        }
        return toTask(taskEntity);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String addTask(Task task) {
        final TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle(task.title());
        taskEntity.setAssignedTo(task.assignedTo());
        taskEntity.setStatus(task.status());
        taskEntity.persist();
        return "/tasks/" + taskEntity.id;
    }

    private Task toTask(TaskEntity taskEntity) {
        return new Task(taskEntity.id, taskEntity.getTitle(), taskEntity.getAssignedTo(), taskEntity.getStatus());
    }
}
