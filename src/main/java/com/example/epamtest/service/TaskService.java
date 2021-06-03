package com.example.epamtest.service;

import com.example.epamtest.dto.TaskDto;
import com.example.epamtest.model.Task;

import java.sql.Time;
import java.util.List;

public interface TaskService {
    TaskDto saveTask(TaskDto taskDto);

    TaskDto saveTs(Task task);

    void deleteTask(Integer id);

    TaskDto findById(Integer id);

    List<TaskDto> findAll();

    TaskDto assignTaskOnUser(Integer taskId, Integer userId);

    List<TaskDto> getTaskOfProjectOfUser(Integer idProject, Integer idUser);

    Time getTimeOfSubtasks(Integer idTask);

    Task getTaskById(Integer id);

    TaskDto assignSubtask(Integer idTask, Integer idSubtask);
}
