package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.scheduler;

import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.ExceptionConstants;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.CmTaskProcessHistoryEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.TaskEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.TaskStateTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class ExpiredTaskCloseScheduler {

    private final TaskJpaRepository taskJpaRepository;
    private final TaskStateTypeJpaRepository taskStateTypeJpaRepository;
    private final TaskSubStateTypeJpaRepository taskSubStateTypeJpaRepository;
    private final TaskStateInformationJpaRepository taskStateInformationJpaRepository;
    private final CmTaskProcessHistoryRepository cmTaskProcessHistoryRepository;

    @Value("${TASK_ENABLED}")
    private Boolean taskEnabled;

    //@Scheduled(fixedDelay = 1000)
    @Scheduled(cron = "${cron.expiredTaskCloseScheduler}")
    public void closedExpiredTasks(){

        if(taskEnabled){

            log.info("Expired Task scheduler started");

            processCmTask();
        }


    }

    private void processCmTask() {
        var taskStateTypeOpen = taskStateTypeJpaRepository.findByName(Constants.TASK_STATE_OPEN)
                .orElseThrow(() -> new CampaignManagementException(ExceptionConstants.TASK_STATE_TYPE_NOT_FOUND));

        var taskStateTypeOnProgress = taskStateTypeJpaRepository.findByName(Constants.TASK_STATE_ON_PROGRESS)
                .orElseThrow(() -> new CampaignManagementException(ExceptionConstants.TASK_STATE_TYPE_NOT_FOUND));

        List<TaskStateTypeEntity> taskStates=new ArrayList<>();
        taskStates.add(taskStateTypeOpen);
        taskStates.add(taskStateTypeOnProgress);

        var taskStateTypeClosed = taskStateTypeJpaRepository.findByName(Constants.TASK_STATE_CLOSED_FAIL)
                .orElseThrow(() -> new CampaignManagementException(ExceptionConstants.TASK_STATE_TYPE_NOT_FOUND));

        var taskSubStateType = taskSubStateTypeJpaRepository.findByName(Constants.TASK_SUB_STATE_EXPIRED)
                .orElseThrow(() -> new CampaignManagementException("task.sub.state.type.not.found"));

        var tasks = taskJpaRepository
                .findAllByTaskStateInformationTaskStateTypeInAndExpireDateBefore(taskStates, LocalDateTime.now());

        var stateInformationList = tasks
                .stream()
                .map(TaskEntity::getTaskStateInformation)
                .toList();

        saveHistory(tasks,taskStateTypeClosed);

        stateInformationList.forEach(state -> {
            state.setTaskStateType(taskStateTypeClosed);
            state.setTaskSubStateType(taskSubStateType);
            state.setNote(taskSubStateType.getDescription());
            taskStateInformationJpaRepository.save(state);
        });
    }

    private void saveHistory(List<TaskEntity> tasks, TaskStateTypeEntity taskStateTypeEntity){


       var history= tasks.stream().map(taskEntity -> {
           var taskProcessHistory = new CmTaskProcessHistoryEntity();
           taskProcessHistory.setTaskId(taskEntity.getId());
           taskProcessHistory.setProcessDate(LocalDateTime.now());
           taskProcessHistory.setTaskStateType(taskStateTypeEntity);
           taskProcessHistory.setUsername(Constants.TASK_PROCESS_HISTORY_USERNAME);
           taskProcessHistory.setName(Constants.TASK_PROCESS_HISTORY_USERNAME);
           taskProcessHistory.setDesc(taskEntity.getTaskStateInformation().getNote());
           return taskProcessHistory;
       }).toList();

       cmTaskProcessHistoryRepository.saveAll(history);
    }

}
