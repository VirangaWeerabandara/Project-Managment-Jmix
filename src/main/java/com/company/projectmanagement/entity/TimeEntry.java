package com.company.projectmanagement.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

@JmixEntity
@Table(name = "TIME_ENTRY", indexes = {
        @Index(name = "IDX_TIME_ENTRY_TASK", columnList = "TASK_ID"),
        @Index(name = "IDX_TIME_ENTRY_ENTRY_DATE", columnList = "ENTRY_DATE_ID")
})
@Entity
public class TimeEntry {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "TASK_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;

    @PositiveOrZero
    @Column(name = "TIME_SPENT", nullable = false)
    @NotNull
    private Integer timeSpent;

    @JoinColumn(name = "ENTRY_DATE_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User entryDate;

    @InstanceName
    @Column(name = "DESCRIPTION", nullable = false)
    @Lob
    @NotNull
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(User entryDate) {
        this.entryDate = entryDate;
    }

    public Integer getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Integer timeSpent) {
        this.timeSpent = timeSpent;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}