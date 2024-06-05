package com.ing.green.micronaut.entity;

import com.ing.green.micronaut.model.TaskStatus;
import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.TypeConverter;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import io.micronaut.data.annotation.TypeDef;
import io.micronaut.data.model.DataType;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.inject.Singleton;

import java.util.Optional;

@Serdeable
@MappedEntity
public class TaskEntity {
    @Id
    private Long id;

    private String title;
    @MappedProperty(value = "assignedto")
    private String assignedTo;
    //@TypeDef(type = DataType.INTEGER, converter = TaskStatusConverter.class)
    private int status;  // FIXME micronaut data handling of enums is simply annoying to figure out. "It should work out of the box... my arse." Pick this up some other time.

    public String getTitle() {
        return title;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public int getStatus() {
        return status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

@Singleton
class TaskStatusConverter implements TypeConverter<Integer, TaskStatus> {

    @Override
    public Optional<TaskStatus> convert(Integer object, Class<TaskStatus> targetType) {
        return TypeConverter.super.convert(object, targetType);
    }

    @Override
    public Optional<TaskStatus> convert(Integer input, Class<TaskStatus> targetType, ConversionContext context) {
        return Optional.of(TaskStatus.values()[input]);
    }
}