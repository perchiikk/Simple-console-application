package com.example.epamtest.service;

import com.example.epamtest.dto.TaskDto;
import com.example.epamtest.dto.UserDto;

import java.util.List;
import java.util.Set;


public interface UserService {
    UserDto saveUser(UserDto userDto);

    void deleteUser(Integer id);

    UserDto findById(Integer id);

    List<UserDto> findAll();

    UserDto assignUserOnProject(Integer idProject, Integer idUser);

    Set<TaskDto> getAllTaskUser(Integer idUser);
}
