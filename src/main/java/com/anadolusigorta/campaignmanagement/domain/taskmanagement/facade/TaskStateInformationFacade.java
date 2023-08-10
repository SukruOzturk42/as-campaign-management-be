package com.anadolusigorta.campaignmanagement.domain.taskmanagement.facade;

import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskStateInformation;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.port.TaskStateInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskStateInformationFacade {

    private final TaskStateInformationRepository taskStateInformationRepository;

    public TaskStateInformation saveTaskState(Long taskId, Long taskStateId, Long taskSubStateId, String note) {
        return taskStateInformationRepository.saveTaskState(taskId,taskStateId, taskSubStateId, note);
    }
}
