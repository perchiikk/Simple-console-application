package com.example.epamtest.service;

import com.example.epamtest.dto.SubtaskDto;
import com.example.epamtest.model.Subtask;

import java.sql.Time;
import java.util.List;

public interface SubtaskService {
    List<SubtaskDto> findAll();

    SubtaskDto findById(Integer id);

    SubtaskDto closeSubtask(Integer id);

    Time getTime(Integer id);

    SubtaskDto save(SubtaskDto subtaskDto);

    Subtask getSubtask(Integer id);
}
