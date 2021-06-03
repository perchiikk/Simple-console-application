package com.example.epamtest.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public class ProjectDto {
    private Integer id;

    private String name;

    private Set<UserDto> userDto;

    public ProjectDto() {
    }

    public ProjectDto(Integer id, String name, Set<UserDto> userDto) {
        this.id = id;
        this.name = name;
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

    public Set<UserDto> getUserDto() {
        return userDto;
    }

    public void setUserDto(Set<UserDto> userDto) {
        this.userDto = userDto;
    }

    @Override
    public String toString() {
        return "ProjectDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userDto=" + userDto +
                '}';
    }
}
