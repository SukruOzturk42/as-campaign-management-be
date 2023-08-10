package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.scheduler;

import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.*;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

@Component
@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class TaskTransferScheduler {

    private final KnimeTaskTypeJpaRepository knimeTaskTypeJpaRepository;
    private final TaskStateTypeJpaRepository taskStateTypeJpaRepository;
    private final TaskStateInformationJpaRepository taskStateInformationJpaRepository;
    private final KnimeTaskJpaRepository knimeTaskJpaRepository;
    private final TaskJpaRepository taskJpaRepository;
    private final CmTaskProcessHistoryRepository cmTaskProcessHistoryRepository;

    @Value("${TASK_ENABLED}")
    private Boolean taskEnabled;

    @Scheduled(cron = "${cron.transferTaskScheduler}")
    public void processTaskState() {
        if (taskEnabled) {
            var knimeTasks = knimeTaskJpaRepository.findAllByIsTransferredFalse();
            var taskState = taskStateTypeJpaRepository.findByName(Constants.TASK_STATE_OPEN);

            for (var knimeTask : knimeTasks) {

                try {
                    var cmTask = knimeTask.toCmTaskModel();
                    var taskEntity = TaskEntity.fromModel(cmTask);
                    var taskStateInformation = new TaskStateInformationEntity();
                    var taskProcessHistory = new CmTaskProcessHistoryEntity();

                    taskStateInformation.setTaskStateType(taskState.orElse(null));
                    taskStateInformation.setNote(taskState.map(TaskStateTypeEntity::getDescription).orElse(null));
                    taskStateInformation = taskStateInformationJpaRepository.save(taskStateInformation);

                    taskEntity.setTaskType(knimeTask.getTaskType());
                    taskEntity.setExpireDate(cmTask.getPeriodStartDate() != null ?
                            cmTask.getPeriodStartDate().plusMonths(1L) : LocalDateTime.now().plusMonths(1L));
                    taskEntity.setTaskStateInformation(taskStateInformation);

                    taskJpaRepository.save(taskEntity);

                    taskProcessHistory.setTaskId(taskEntity.getId());
                    taskProcessHistory.setProcessDate(LocalDateTime.now());
                    taskProcessHistory.setTaskStateType(taskState.orElseThrow(() ->
                            new CampaignManagementException("task.state.type.not.found")));
                    taskProcessHistory.setUsername(Constants.TASK_PROCESS_HISTORY_USERNAME);
                    taskProcessHistory.setName(Constants.TASK_PROCESS_HISTORY_FULL_NAME);
                    taskProcessHistory.setDesc(taskStateInformation.getNote());
                    cmTaskProcessHistoryRepository.save(taskProcessHistory);

                    knimeTask.setIsTransferred(Boolean.TRUE);
                    knimeTaskJpaRepository.save(knimeTask);
                }
                catch(Exception e) {
                    log.info(String.format("Task transfer exception %s",e.getMessage()));
                }
            }
        }

    }

    private String getTaskOwnerUserName(KnimeTaskEntity knimeTaskEntity){
        return null;
    }
}
