package com.example.epamtest.mapper;

import com.example.epamtest.dto.TaskDto;
import com.example.epamtest.model.Task;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {
    TaskMapper TASK_MAPPER = Mappers.getMapper(TaskMapper.class);

    @Mapping(source = "user", target = "userDto")
    TaskDto fromTask(Task task);

    @InheritInverseConfiguration
    Task fromTaskDto(TaskDto taskDto);
}
