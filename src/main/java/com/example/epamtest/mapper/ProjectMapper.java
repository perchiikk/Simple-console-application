package com.example.epamtest.mapper;

import com.example.epamtest.dto.ProjectDto;
import com.example.epamtest.model.Project;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMapper {
    ProjectMapper PROJECT_MAPPER = Mappers.getMapper(ProjectMapper.class);

    @Mapping(source = "user", target = "userDto")
    ProjectDto fromProject(Project project);

    @InheritInverseConfiguration
    Project fromProjectDto(ProjectDto projectDto);
}
