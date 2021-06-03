package com.example.epamtest.mapper;

import com.example.epamtest.dto.SubtaskDto;
import com.example.epamtest.model.Subtask;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubtaskMapper {
    SubtaskMapper SUBTASK_MAPPER = Mappers.getMapper(SubtaskMapper.class);

    @Mapping(source = "task", target = "taskDto")
    @Mapping(source = "parent", target = "parentDto")
    SubtaskDto fromSubtask(Subtask subtask);

    @InheritInverseConfiguration
    Subtask fromSudtaskDto(SubtaskDto subtaskDto);
}
