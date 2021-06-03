package com.example.epamtest.service.impl;

import com.example.epamtest.dto.ProjectDto;
import com.example.epamtest.dto.UserDto;
import com.example.epamtest.exception.ValidationException;
import com.example.epamtest.mapper.ProjectMapper;
import com.example.epamtest.model.Project;
import com.example.epamtest.repository.ProjectRepository;
import com.example.epamtest.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    private void validProjectDto(ProjectDto projectDto) throws ValidationException {
        if (projectDto == null) {
            throw new ValidationException("Object is null");
        }
        if(projectDto.getId() == null && projectDto.getName() == null && projectDto.getUserDto() == null){
            throw new ValidationException("Object is null");
        }
    }


    @Override
    public ProjectDto saveProject(ProjectDto projectDto) {
        validProjectDto(projectDto);

        Project project = ProjectMapper.PROJECT_MAPPER.fromProjectDto(projectDto);
        projectRepository.save(project);

        return ProjectMapper.PROJECT_MAPPER.fromProject(project);
    }


    @Override
    public void deleteUser(Integer id) {
        projectRepository.deleteById(id);
    }


    @Override
    public ProjectDto findById(Integer id) {
        Optional<Project> project = projectRepository.findById(id);
        return project.map(ProjectMapper.PROJECT_MAPPER::fromProject).orElse(null);
    }

    @Override
    public Project findByIdProject(Integer id) {
        Optional<Project> project = projectRepository.findById(id);
        return project.orElse(null);
    }


    @Override
    public List<ProjectDto> findAll() {
        List<ProjectDto> projectDtoList = new ArrayList<>();
        List<Project> projectList = projectRepository.findAll();
        for (Project project : projectList) {
            projectDtoList.add(ProjectMapper.PROJECT_MAPPER.fromProject(project));
        }
        return projectDtoList;
    }

    @Override
    public Set<UserDto> getAllUserOfProject(Integer idProject) {
        Set<UserDto> userDtoSet = new HashSet<>();
        ProjectDto projectDto = findById(idProject);
        userDtoSet = projectDto.getUserDto();
        return userDtoSet;
    }

}
