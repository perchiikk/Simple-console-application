package com.example.epamtest.service.impl;

import com.example.epamtest.dto.TaskDto;
import com.example.epamtest.dto.UserDto;
import com.example.epamtest.exception.ValidationException;
import com.example.epamtest.mapper.SubtaskMapper;
import com.example.epamtest.mapper.TaskMapper;
import com.example.epamtest.mapper.UserMapper;
import com.example.epamtest.model.Project;
import com.example.epamtest.model.Subtask;
import com.example.epamtest.model.Task;
import com.example.epamtest.model.User;
import com.example.epamtest.repository.TaskRepository;
import com.example.epamtest.service.ProjectService;
import com.example.epamtest.service.SubtaskService;
import com.example.epamtest.service.TaskService;
import com.example.epamtest.service.UserService;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;
    private final ProjectService projectService;
    private final SubtaskService subtaskService;

    public TaskServiceImpl(TaskRepository taskRepository, UserService userService, ProjectService projectService,
                           SubtaskService subtaskService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
        this.projectService = projectService;
        this.subtaskService = subtaskService;
    }

    private void validTask(TaskDto taskDto) throws ValidationException {
        if (taskDto == null) {
            throw new ValidationException("Object is null");
        }
        if (taskDto.getId() == null && taskDto.getName() == null && taskDto.getUserDto() == null) {
            throw new ValidationException("Object is null");
        }
    }

    @Override
    public TaskDto saveTask(TaskDto taskDto) {
        validTask(taskDto);
        Task task = TaskMapper.TASK_MAPPER.fromTaskDto(taskDto);
        taskRepository.save(task);

        return TaskMapper.TASK_MAPPER.fromTask(task);
    }

    @Override
    public TaskDto saveTs(Task task) {
        taskRepository.save(task);
        return TaskMapper.TASK_MAPPER.fromTask(task);
    }

    @Override
    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }

    @Override
    public TaskDto findById(Integer id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.map(TaskMapper.TASK_MAPPER::fromTask).orElse(null);
    }

    @Override
    public List<TaskDto> findAll() {
        List<TaskDto> taskDtoList = new ArrayList<>();
        List<Task> taskList = taskRepository.findAll();
        for (Task task : taskList) {
            taskDtoList.add(TaskMapper.TASK_MAPPER.fromTask(task));
        }
        return taskDtoList;
    }

    @Override
    public TaskDto assignTaskOnUser(Integer taskId, Integer userId) {
        TaskDto taskDto = findById(taskId);
        Task task = TaskMapper.TASK_MAPPER.fromTaskDto(taskDto);

        UserDto userDto = userService.findById(userId);
        User user = UserMapper.USER_MAPPER.fromUserDto(userDto);
        task.setUser(user);
        taskRepository.save(task);
        userService.saveUser(UserMapper.USER_MAPPER.fromUser(user));

        return TaskMapper.TASK_MAPPER.fromTask(task);
    }

    @Override
    public List<TaskDto> getTaskOfProjectOfUser(Integer idProject, Integer idUser) {
        Project project = projectService.findByIdProject(idProject);
        Set<User> userDtoSet = project.getUser();
        List<Task> taskList = new ArrayList<>();
        for (User user : userDtoSet) {
            if (user.getId() == idUser) {
                taskList = new ArrayList<>(user.getTask());
                break;
            }
        }
        return taskList.stream().map(TaskMapper.TASK_MAPPER::fromTask).collect(Collectors.toList());
    }

    @Override
    public Time getTimeOfSubtasks(Integer idTask) {
        LocalTime timeLeft = LocalTime.of(0, 0);
        int second = 0;
        int minute = 0;
        int hour = 0;
        Task task = getTaskById(idTask);
        if (task != null) {
            Set<Subtask> subtaskSet = task.getSubtaskSet();
            if (subtaskSet != null && subtaskSet.size() > 0) {
                for (Subtask subtask : subtaskSet) {
                    Time time = subtask.getTimeLeft();
                    LocalTime localTime = time.toLocalTime();
                    second += localTime.getSecond();
                    minute += localTime.getMinute();
                    hour += localTime.getHour();
                }
            }
        }

        timeLeft = timeLeft.plusSeconds(second).plusMinutes(minute).plusHours(hour);
        return Time.valueOf(timeLeft);
    }

    @Override
    public Task getTaskById(Integer id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public TaskDto assignSubtask(Integer idTask, Integer idSubtask) {
        Subtask subtask = subtaskService.getSubtask(idSubtask);
        Task task = getTaskById(idTask);
        subtask.setTask(task);
        task.getSubtaskSet().add(subtask);
        subtaskService.save(SubtaskMapper.SUBTASK_MAPPER.fromSubtask(subtask));
        saveTs(task);
        return TaskMapper.TASK_MAPPER.fromTask(task);
    }
}
