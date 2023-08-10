package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.CreateTaskGroup;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskGroup;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskType;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.port.TaskGroupRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.CampaignRuleGroupJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.ExceptionConstants;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.KnimeTaskTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.TaskGroupEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.repository.KnimeTaskTypeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.repository.TaskGroupJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class TaskGroupJpaAdapter implements TaskGroupRepository {

    private final TaskGroupJpaRepository taskGroupJpaRepository;
    private final KnimeTaskTypeJpaRepository taskTypeJpaRepository;
    private final CampaignRuleGroupJpaRepository campaignRuleGroupJpaRepository;

    @Override
    public TaskGroup save(CreateTaskGroup taskGroup) {
        var entity = new TaskGroupEntity();
        var taskType = taskTypeJpaRepository.findById(taskGroup.getTaskTypeId())
                .orElseThrow(() -> new CampaignManagementException("task.type.not.found"));
        var policyNumbers = taskGroup.getPolicyNumbers().stream()
                .map(i -> i.split("-")[0])
                .collect(Collectors.toList());
        entity.setId(taskGroup.getId());
        entity.setTaskType(taskType);
        entity.setPolicyNumbers(!policyNumbers.isEmpty() ? String.join(Constants.ATTRIBUTE_VALUE_DELIMITER + " ", policyNumbers) : null);
        entity.setDescription(taskGroup.getDescription());
        entity.setEndDate(taskGroup.getEndDate().toLocalDate().atTime(LocalTime.MAX));
        entity.setStartDate(taskGroup.getStartDate().toLocalDate().atStartOfDay());
        entity.setText(taskGroup.getText());
        entity.setName(taskGroup.getName());
        entity.setGoal(taskGroup.getGoal());

        return taskGroupJpaRepository
                .save(entity)
                .toModel();
    }

    @Override
    public TaskGroup delete(Long taskGroupId) {
        var taskGroupEntity = taskGroupJpaRepository.findById(taskGroupId)
                .orElseThrow(() -> new CampaignManagementException(ExceptionConstants.TASK_GROUP_NOT_FOUND));
        if (!campaignRuleGroupJpaRepository.findByTaskGroupId(taskGroupId).isEmpty()) {
            throw new CampaignManagementException("task.groups.use.in.campaign.rule.group.cannot.delete");
        }
        taskGroupJpaRepository.delete(taskGroupEntity);
        return taskGroupEntity.toModel();

    }

    @Override
    public List<TaskGroup> getAllTaskGroupsByDates(LocalDateTime start, LocalDateTime end) {
        return taskGroupJpaRepository.findByStartDateAfterAndEndDateIsBefore(start, end)
                .stream()
                .map(TaskGroupEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskGroup> getAllTaskGroups() {
        return taskGroupJpaRepository.findByEndDateAfter(LocalDateTime.now())
                .stream()
                .map(TaskGroupEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public TaskGroup update(CreateTaskGroup taskGroup) {
        var taskType = taskTypeJpaRepository.findById(taskGroup.getTaskTypeId())
                .orElseThrow(() -> new CampaignManagementException("task.type.not.found"));
        var entity = taskGroupJpaRepository.findById(taskGroup.getId())
                .orElseThrow(() -> new CampaignManagementException(ExceptionConstants.TASK_GROUP_NOT_FOUND));
        entity.setDescription(taskGroup.getDescription());
        entity.setEndDate(taskGroup.getEndDate());
        entity.setStartDate(taskGroup.getStartDate());
        entity.setText(taskGroup.getText());
        entity.setName(taskGroup.getName());
        entity.setTaskType(taskType);
        entity.setGoal(taskGroup.getGoal());

        return taskGroupJpaRepository
                .save(entity)
                .toModel();
    }

    @Override
    public TaskGroup getTaskGroupById(Long id) {
        return taskGroupJpaRepository.findById(id)
                .orElseThrow(() -> new CampaignManagementException(ExceptionConstants.TASK_GROUP_NOT_FOUND))
                .toModel();
    }

    @Override
    public Set<TaskType> getTaskGroupTaskTypes() {

        return taskGroupJpaRepository.findAll().stream()
                .map(TaskGroupEntity::getTaskType)
                .map(KnimeTaskTypeEntity::toModel)
                .collect(Collectors.toSet());
    }
}
