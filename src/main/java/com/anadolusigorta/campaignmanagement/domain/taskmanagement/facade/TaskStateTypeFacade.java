package com.anadolusigorta.campaignmanagement.domain.taskmanagement.facade;

import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskStateType;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskSubStateType;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.port.TaskStateTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskStateTypeFacade {

    private final TaskStateTypeRepository taskStateTypeRepository;

    public List<TaskStateType> getStateTypes() {
        return taskStateTypeRepository.getStateTypes();
    }

    public List<TaskStateType> getFilteredStateTypes() {
        return taskStateTypeRepository.getFilteredStateTypes();
    }

    public List<TaskStateType> getSubStateTypes(Long taskStateTypeId) {
        return taskStateTypeRepository.getSubStateTypesByTaskStateTypeId(taskStateTypeId);
    }

    public List<TaskStateType> getSubStateTypesByStateTypes(List<Long> taskStateTypes) {
        return taskStateTypeRepository.getSubStateTypesByStateTypes(taskStateTypes);
    }
}
