package com.example.epamtest.service.impl;

import com.example.epamtest.dto.ProjectDto;
import com.example.epamtest.dto.TaskDto;
import com.example.epamtest.dto.UserDto;
import com.example.epamtest.exception.ValidationException;
import com.example.epamtest.mapper.ProjectMapper;
import com.example.epamtest.mapper.UserMapper;
import com.example.epamtest.model.Project;
import com.example.epamtest.model.User;
import com.example.epamtest.repository.UserRepository;
import com.example.epamtest.service.ProjectService;
import com.example.epamtest.service.UserService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ProjectService projectService;

    public UserServiceImpl(UserRepository userRepository, ProjectService projectService) {
        this.userRepository = userRepository;
        this.projectService = projectService;
    }

    private void validateUSerDto(UserDto userDto) throws ValidationException {
        if (userDto == null) {
            throw new ValidationException("Object is null");
        }
        if (userDto.getId() == null && userDto.getName() == null && userDto.getTaskDto() == null
                && userDto.getProjectDto() == null) {
            throw new ValidationException("Object is null");
        }
    }

    @Override
    public UserDto saveUser(UserDto userDto) throws ValidationException {
        validateUSerDto(userDto);

        User user = UserMapper.USER_MAPPER.fromUserDto(userDto);
        userRepository.save(user);

        return UserMapper.USER_MAPPER.fromUser(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(UserMapper.USER_MAPPER::fromUser).orElse(null);
    }

    @Override
    public List<UserDto> findAll() {
        List<UserDto> userDtoList = new ArrayList<>();
        List<User> userList = userRepository.findAll();
        for (User user : userList) {
            userDtoList.add(UserMapper.USER_MAPPER.fromUser(user));
        }
        return userDtoList;
    }

    @Override
    public UserDto assignUserOnProject(Integer idProject, Integer idUser) {
        UserDto userDto = findById(idUser);
        User user = UserMapper.USER_MAPPER.fromUserDto(userDto);

        ProjectDto projectDto = projectService.findById(idProject);
        Project project = ProjectMapper.PROJECT_MAPPER.fromProjectDto(projectDto);

        user.setProject(project);
        userRepository.save(user);
        projectService.saveProject(ProjectMapper.PROJECT_MAPPER.fromProject(project));

        return UserMapper.USER_MAPPER.fromUser(user);
    }

    @Override
    public Set<TaskDto> getAllTaskUser(Integer idUser) {
        Set<TaskDto> taskDtoSet = new HashSet<>();
        UserDto userDto = findById(idUser);
        taskDtoSet = userDto.getTaskDto();
        return taskDtoSet;
    }


}
