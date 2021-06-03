package com.example.epamtest.service;

import com.example.epamtest.dto.ProjectDto;
import com.example.epamtest.dto.UserDto;
import com.example.epamtest.model.Project;

import java.util.List;
import java.util.Set;

public interface ProjectService {
    ProjectDto saveProject(ProjectDto projectDto);

    void deleteUser(Integer id);

    ProjectDto findById(Integer id);

    Project findByIdProject(Integer id);

    List<ProjectDto> findAll();

    Set<UserDto> getAllUserOfProject(Integer idProject);
}
