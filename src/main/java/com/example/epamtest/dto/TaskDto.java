package com.example.epamtest.dto;

import lombok.Builder;

@Builder
public class TaskDto {
    private Integer id;

    private String name;

    private boolean closed;

    private UserDto userDto;

    public TaskDto() {
    }

    public TaskDto(Integer id, String name, boolean closed, UserDto userDto) {
        this.id = id;
        this.name = name;
        this.closed = closed;
        this.userDto = userDto;
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

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    @Override
    public String toString() {
        return "TaskDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", closed=" + closed +
                ", userDto=" + userDto +
                '}';
    }
}
