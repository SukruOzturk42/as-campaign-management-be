package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.facade.TaskProcessHistoryFacade;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskProcessHistory;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskStateInformation;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskStateType;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.port.TaskStateInformationRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.TaskStateTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.repository.TaskJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.repository.TaskStateInformationJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.repository.TaskStateTypeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.adapter.UserRepositoryJpaAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class TaskStateInformationJpaAdapter implements TaskStateInformationRepository {

    private final TaskStateInformationJpaRepository taskStateInformationJpaRepository;
    private final TaskJpaRepository taskJpaRepository;
    private final TaskStateTypeJpaRepository taskStateTypeJpaRepository;
    private final TaskProcessHistoryFacade taskProcessHistoryFacade;
    private final UserRepositoryJpaAdapter userRepositoryJpaAdapter;

    @Override
    public TaskStateInformation saveTaskState(Long taskId, Long taskStateId, Long taskSubStateId, String note) {
        var task = taskJpaRepository.findById(taskId)
                .orElseThrow(() -> new CampaignManagementException("task.not.found"));
        var taskStateInformationEntity = task.getTaskStateInformation();
        var taskStateType = taskStateTypeJpaRepository.findById(taskStateId)
                .orElseThrow(() -> new CampaignManagementException("task.state.type.not.found"));
        taskStateInformationEntity.setTaskStateType(taskStateType);
        taskStateInformationEntity.setTaskSubStateType(taskStateType.getTaskSubStateTypes()
                .stream()
                .filter(subState -> subState.getId().equals(taskSubStateId))
                .findFirst()
                .orElseThrow(()-> new CampaignManagementException("task.sub.state.not.found")));
        taskStateInformationEntity.setNote(note);
        taskProcessHistoryFacade.saveHistory(toModel(note,taskId,taskStateType.toModel()));
        return taskStateInformationJpaRepository.save(taskStateInformationEntity).toModel();
    }

    private TaskProcessHistory toModel(String note, Long taskId, TaskStateType taskStateType){

        var user = userRepositoryJpaAdapter.getUserFromContext();

        return TaskProcessHistory.builder()
                .taskId(taskId)
                .taskState(taskStateType)
                .username(user.getUsername())
                .name(user.getFullName())
                .desc(note)
                .processDate( LocalDateTime.now())
                .build();
    }


}
