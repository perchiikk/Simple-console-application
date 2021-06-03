package com.example.epamtest.dto;

import java.sql.Time;

public class SubtaskDto {
    private Integer id;

    private String name;

    private boolean closed;

    private Time timeLeft;

    private TaskDto taskDto;

    private SubtaskDto parentDto;

    public SubtaskDto() {
    }

    public SubtaskDto(Integer id, String name, boolean closed, Time timeLeft, TaskDto taskDto, SubtaskDto parentDto) {
        this.id = id;
        this.name = name;
        this.closed = closed;
        this.timeLeft = timeLeft;
        this.taskDto = taskDto;
        this.parentDto = parentDto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaskDto getTaskDto() {
        return taskDto;
    }

    public void setTaskDto(TaskDto taskDto) {
        this.taskDto = taskDto;
    }

    public SubtaskDto getParentDto() {
        return parentDto;
    }

    public void setParentDto(SubtaskDto parentDto) {
        this.parentDto = parentDto;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Time getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(Time timeLeft) {
        this.timeLeft = timeLeft;
    }

    @Override
    public String toString() {
        return "SubtaskDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", closed=" + closed +
                ", timeLeft=" + timeLeft +
                ", taskDto=" + taskDto +
                ", parentDto=" + parentDto +
                '}';
    }
}
