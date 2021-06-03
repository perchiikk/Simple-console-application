package com.example.epamtest.service.impl;

import com.example.epamtest.dto.ProjectDto;
import com.example.epamtest.dto.SubtaskDto;
import com.example.epamtest.exception.ValidationException;
import com.example.epamtest.mapper.SubtaskMapper;
import com.example.epamtest.model.Subtask;
import com.example.epamtest.repository.SubtaskRepository;
import com.example.epamtest.service.SubtaskService;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class SubtaskServiceImpl implements SubtaskService {
    private final SubtaskRepository subtaskRepository;

    public SubtaskServiceImpl(SubtaskRepository subtaskRepository) {
        this.subtaskRepository = subtaskRepository;
    }

    @Override
    public List<SubtaskDto> findAll() {
        List<Subtask> subtaskList = subtaskRepository.findAll();
        List<SubtaskDto> subtaskDtoList = new ArrayList<>();
        for (Subtask subtask : subtaskList) {
            subtaskDtoList.add(SubtaskMapper.SUBTASK_MAPPER.fromSubtask(subtask));
        }
        return subtaskDtoList;
    }

    @Override
    public SubtaskDto findById(Integer id) {
        Optional<Subtask> subtask = subtaskRepository.findById(id);
        return subtask.map(SubtaskMapper.SUBTASK_MAPPER::fromSubtask).orElse(null);
    }

    @Override
    public SubtaskDto closeSubtask(Integer id) {
        Subtask subtask = getSubtask(id);
        subtask.setClosed(true);
        if (subtask.getSubtaskSet() != null && subtask.getSubtaskSet().size() > 0) {
            for (Subtask subtaskCurrent : subtask.getSubtaskSet()) {
                subtaskCurrent.setClosed(true);
                subtaskRepository.save(subtaskCurrent);
            }
        }
        subtaskRepository.save(subtask);
        return SubtaskMapper.SUBTASK_MAPPER.fromSubtask(subtask);
    }

    @Override
    public Time getTime(Integer id) {
        return null;
    }

    @Override
    public SubtaskDto save(SubtaskDto subtaskDto) throws ValidationException {
        validSubtaskDto(subtaskDto);
        Subtask subtask = SubtaskMapper.SUBTASK_MAPPER.fromSudtaskDto(subtaskDto);
        subtaskRepository.save(subtask);
        return SubtaskMapper.SUBTASK_MAPPER.fromSubtask(subtask);
    }

    @Override
    public Subtask getSubtask(Integer id) {
        return subtaskRepository.findById(id).orElse(null);
    }


    private void validSubtaskDto(SubtaskDto subtaskDto) throws ValidationException {
        if (subtaskDto == null) {
            throw new ValidationException("Object is null");
        }
        if (subtaskDto.getId() == null && subtaskDto.getName() == null && subtaskDto.getTimeLeft() == null
                && subtaskDto.getTaskDto() == null && subtaskDto.getParentDto() == null) {
            throw new ValidationException("Object is null");
        }
    }
}
