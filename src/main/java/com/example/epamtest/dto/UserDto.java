package com.example.epamtest.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public class UserDto {
    private Integer id;

    private String name;

    private Set<TaskDto> taskDto;

    private ProjectDto projectDto;

    public UserDto() {
    }

    public UserDto(Integer id, String name, Set<TaskDto> taskDto, ProjectDto projectDto) {
        this.id = id;
        this.name = name;
        this.taskDto = taskDto;
        this.projectDto = projectDto;
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

    public Set<TaskDto> getTaskDto() {
        return taskDto;
    }

    public void setTaskDto(Set<TaskDto> taskDto) {
        this.taskDto = taskDto;
    }

    public ProjectDto getProjectDto() {
        return projectDto;
    }

    public void setProjectDto(ProjectDto projectDto) {
        this.projectDto = projectDto;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", taskDto=" + taskDto +
                ", projectDto=" + projectDto +
                '}';
    }
}
