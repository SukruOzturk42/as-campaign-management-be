package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskStateType;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskSubStateType;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.port.TaskStateTypeRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.TaskStateTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.TaskSubStateTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.repository.TaskStateTypeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.adapter.UserRepositoryJpaAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.anadolusigorta.campaignmanagement.domain.common.Constants.TASK_STATE_CLOSED_SUCCESS;
import static com.anadolusigorta.campaignmanagement.domain.common.Constants.TASK_STATE_OPEN;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class TaskStateTypeJpaAdapter implements TaskStateTypeRepository {

    private final TaskStateTypeJpaRepository taskStateTypeJpaRepository;
    private final UserRepositoryJpaAdapter userRepositoryJpaAdapter;

    @Override
    public List<TaskStateType> getStateTypes() {
        return taskStateTypeJpaRepository.findAll().stream()
                .map(TaskStateTypeEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskStateType> getFilteredStateTypes() {
        return taskStateTypeJpaRepository.findAll().stream()
                .filter(state -> !state.getName().equals(TASK_STATE_OPEN) &&
                        !state.getName().equals(TASK_STATE_CLOSED_SUCCESS))
                .map(TaskStateTypeEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskStateType> getSubStateTypesByStateTypes(List<Long> taskStateTypes) {
        var taskSubStates = new ArrayList<TaskSubStateTypeEntity>();
        var roleName = userRepositoryJpaAdapter.getUserFromContext().getUserRole().getName();
        taskStateTypeJpaRepository.findByIdIn(taskStateTypes)
                .orElseThrow(() -> new CampaignManagementException("task.sub.state.type.not.found"))
                .forEach(i -> taskSubStates.addAll(i.getTaskSubStateTypes()));
        var taskSubStateTypes = taskSubStates.stream().map(TaskSubStateTypeEntity::toModel).toList();
        if (roleName.equals(Constants.AGENCY_USER)) {
            return taskSubStateTypes.stream().filter(item -> !item.getName().equals(Constants.TASK_SUB_STATE_CLOSED_FAIL_AGENCY) &&
                    !item.getName().equals(Constants.TASK_SUB_STATE_CLOSED_FAIL_INSURANCE_COMPANY)).collect(Collectors.toList());
        } else {
            return taskSubStateTypes;
        }
    }

    @Override
    public List<TaskStateType> getSubStateTypesByTaskStateTypeId(Long taskStateTypeId) {
        var taskStateTypeEntity = taskStateTypeJpaRepository.findById(taskStateTypeId)
                .orElseThrow(() -> new CampaignManagementException("task.state.type.not.found"));
        var roleCode = userRepositoryJpaAdapter.getUserFromContext().getUserRole().getCode().toString();
        return taskStateTypeEntity.getTaskSubStateTypes()
                .stream()
                .filter(item -> item.getAccessRoleCodes().contains(roleCode))
                .map(TaskSubStateTypeEntity::toModel)
                .collect(Collectors.toList());
    }


}
