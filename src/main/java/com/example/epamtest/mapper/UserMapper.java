package com.example.epamtest.mapper;

import com.example.epamtest.dto.UserDto;
import com.example.epamtest.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "task", target = "taskDto")
    @Mapping(source = "project", target = "projectDto")
    UserDto fromUser(User user);

    @InheritInverseConfiguration
    @Mapping(source = "taskDto", target = "task")
    @Mapping(source = "projectDto", target = "project")
    User fromUserDto(UserDto userDto);
}
