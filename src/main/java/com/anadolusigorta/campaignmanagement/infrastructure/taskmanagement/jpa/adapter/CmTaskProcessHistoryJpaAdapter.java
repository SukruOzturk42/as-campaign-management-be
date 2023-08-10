package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskProcessHistory;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.port.TaskProcessHistoryRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.CmTaskProcessHistoryEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.repository.CmTaskProcessHistoryRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.repository.TaskStateTypeJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CmTaskProcessHistoryJpaAdapter implements TaskProcessHistoryRepository {

    private final CmTaskProcessHistoryRepository taskProcessHistoryRepository;
    private final TaskStateTypeJpaRepository taskStateTypeJpaRepository;

    @Override
    public TaskProcessHistory saveHistory(TaskProcessHistory taskProcessHistory) {
        var taskProcessHistoryEntity = new CmTaskProcessHistoryEntity();
        var taskStateTypeEntity = taskStateTypeJpaRepository.findById(taskProcessHistory.getTaskState().getId())
                .orElseThrow(() -> new CampaignManagementException("task.state.type.not.found"));
        taskProcessHistoryEntity.setName(taskProcessHistory.getName());
        taskProcessHistoryEntity.setDesc(taskProcessHistory.getDesc());
        taskProcessHistoryEntity.setProcessDate(taskProcessHistory.getProcessDate());
        taskProcessHistoryEntity.setUsername(taskProcessHistory.getUsername());
        taskProcessHistoryEntity.setTaskId(taskProcessHistory.getTaskId());
        taskProcessHistoryEntity.setTaskStateType(taskStateTypeEntity);
        return taskProcessHistoryRepository.save(taskProcessHistoryEntity).toModel();
    }

    @Override
    public List<TaskProcessHistory> getTaskProcessHistories() {
        return taskProcessHistoryRepository.findAll()
                .stream()
                .map(CmTaskProcessHistoryEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskProcessHistory> getTaskProcessHistoriesByTaskId(Long taskId) {
        return taskProcessHistoryRepository.findAllByTaskId(taskId)
                .stream()
                .map(CmTaskProcessHistoryEntity::toModel)
                .collect(Collectors.toList());
    }
}
