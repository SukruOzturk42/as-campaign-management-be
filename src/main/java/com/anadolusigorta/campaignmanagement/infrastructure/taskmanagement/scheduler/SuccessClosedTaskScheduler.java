package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.scheduler;

import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.infrastructure.common.configuration.scheduler.taskmanagement.TaskManagementSchedulerConfigurationProperties;
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

@Component
@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SuccessClosedTaskScheduler {

    private final TaskJpaRepository taskJpaRepository;
    private final TaskStateTypeJpaRepository taskStateTypeJpaRepository;

    private final TaskSubStateTypeJpaRepository taskSubStateTypeJpaRepository;
    private final TaskStateInformationJpaRepository taskStateInformationJpaRepository;
    private final AsPolicySaleJpaRepository asPolicySaleJpaRepository;
    private final CmTaskProcessHistoryRepository cmTaskProcessHistoryRepository;

    private final TaskGroupJpaRepository taskGroupJpaRepository;

    private final TaskManagementSchedulerConfigurationProperties taskManagementSchedulerConfigurationProperties;


    @Value("${TASK_ENABLED}")
    private Boolean taskEnabled;

    @Scheduled(cron = "${cron.successTaskCloseScheduler}")
    public void closeTasks(){

        log.info("Success Task Close scheduler started.");

        if (taskEnabled) {
            try {

                var taskStateTypeCloseSuccess = taskStateTypeJpaRepository.findByName(Constants.TASK_STATE_CLOSED_SUCCESS)
                        .orElseThrow(() -> new CampaignManagementException("task.state.type.not.found"));

                var taskStateTypeCloseFail = taskStateTypeJpaRepository.findByName(Constants.TASK_STATE_CLOSED_FAIL)
                        .orElseThrow(() -> new CampaignManagementException("task.state.type.not.found"));

                var taskSubStateCancel=taskSubStateTypeJpaRepository.findByName(Constants.TASK_SUB_STATE_CANCEL_POLICY)
                        .orElseThrow(() -> new CampaignManagementException("task.sub.state.type.not.found"));

                var startDate = LocalDateTime.now().plusDays(-31);
                var endDate = LocalDateTime.now().plusDays(-1);

                var tasks = taskJpaRepository.findAllByTaskStateInformationTaskStateTypeAndUpdatedAtBetween(taskStateTypeCloseSuccess, startDate, endDate);

                for (var task : tasks) {

                    var policyNumber=task.getPolicyNumber().split("/")[0];



                }

            }
            catch (Exception e) {
                log.info(String.format("E->%s", e.getMessage()));
            }
        }

        log.info("Success Task Close scheduler ended.");

    }

    private void addHistoryRecord(TaskEntity activeTask, TaskStateInformationEntity taskStateInformation, TaskStateTypeEntity recordState) {
        var taskProcessHistory = new CmTaskProcessHistoryEntity();
        taskProcessHistory.setTaskId(activeTask.getId());
        taskProcessHistory.setProcessDate(LocalDateTime.now());
        taskProcessHistory.setTaskStateType(recordState);
        taskProcessHistory.setUsername(Constants.TASK_PROCESS_HISTORY_USERNAME);
        taskProcessHistory.setName(Constants.TASK_PROCESS_HISTORY_FULL_NAME);
        taskProcessHistory.setDesc(taskStateInformation.getNote());
        cmTaskProcessHistoryRepository.save(taskProcessHistory);
    }
}
