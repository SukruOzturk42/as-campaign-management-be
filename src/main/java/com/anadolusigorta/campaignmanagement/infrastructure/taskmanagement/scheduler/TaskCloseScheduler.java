package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.scheduler;

import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.infrastructure.common.configuration.scheduler.taskmanagement.TaskManagementSchedulerConfigurationProperties;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.ExceptionConstants;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class TaskCloseScheduler {

    private final TaskJpaRepository taskJpaRepository;
    private final TaskStateTypeJpaRepository taskStateTypeJpaRepository;

    private final TaskSubStateTypeJpaRepository taskSubStateTypeJpaRepository;
    private final TaskStateInformationJpaRepository taskStateInformationJpaRepository;
    private final AsPolicySaleJpaRepository asPolicySaleJpaRepository;
    private final CmTaskProcessHistoryRepository cmTaskProcessHistoryRepository;

    private final TaskGroupJpaRepository taskGroupJpaRepository;

    private final TaskManagementSchedulerConfigurationProperties taskManagementSchedulerConfigurationProperties;


    private final AsRobotPolicyProposalJpaRepository asRobotPolicyProposalJpaRepository;


    @Value("${TASK_ENABLED}")
    private Boolean taskEnabled;

    //@Scheduled(fixedDelay = 1000L)
    @Scheduled(cron = "${cron.taskCloseScheduler}")
    public void closeTasks() {


        if (taskEnabled) {
            log.info("Task Close scheduler started.");
            try {

                var taskStateTypeCloseSuccess = taskStateTypeJpaRepository.findByName(Constants.TASK_STATE_CLOSED_SUCCESS)
                        .orElseThrow(() -> new CampaignManagementException(ExceptionConstants.TASK_STATE_TYPE_NOT_FOUND));

                var taskStateTypeCloseFail = taskStateTypeJpaRepository.findByName(Constants.TASK_STATE_CLOSED_FAIL)
                        .orElseThrow(() -> new CampaignManagementException(ExceptionConstants.TASK_STATE_TYPE_NOT_FOUND));

                var taskSubStateTypeCloseFail = taskSubStateTypeJpaRepository.findByName(Constants.TASK_SUB_STATE_CLOSED_FAIL_AGENCY)
                        .orElseThrow(() -> new CampaignManagementException(ExceptionConstants.TASK_STATE_TYPE_NOT_FOUND));

                var taskSubStateTypeCloseFailDigitalAgency = taskSubStateTypeJpaRepository.findByName(Constants.TASK_SUB_STATE_CLOSED_FAIL_DIGITAL_AGENCY)
                        .orElseThrow(() -> new CampaignManagementException(ExceptionConstants.TASK_STATE_TYPE_NOT_FOUND));

                var stateNames = new ArrayList<String>();
                stateNames.add(Constants.TASK_STATE_OPEN);
                stateNames.add(Constants.TASK_STATE_ON_PROGRESS);
                var states = taskStateTypeJpaRepository.findByNameIn(stateNames);

                var taskGroups = taskGroupJpaRepository
                        .findByStartDateBeforeAndEndDateIsAfter(LocalDateTime.now(), LocalDateTime.now());


                for (var taskGroup : taskGroups) {

                log.info(String.format(" Task Group task.group.ıd:%s task.group.name:%s task.group.type.name:%s",taskGroup.getId(),taskGroup.getName(),taskGroup.getTaskType().getName()));

                    var activeTasks = taskJpaRepository
                            .findAllByTaskTypeIdAndTaskStateInformationTaskStateTypeInAndExpireDateAfter(taskGroup.getTaskType().getId(), states, LocalDateTime.now());

                    closeTasksOfGroup(taskGroup, taskStateTypeCloseSuccess, taskStateTypeCloseFail, taskSubStateTypeCloseFail, taskSubStateTypeCloseFailDigitalAgency, activeTasks);
                }
            } catch (Exception e) {
                log.info(String.format("Task Close scheduler exception->%s", e.getMessage()));
            }
        }

        log.info("Task Close scheduler ended.");

    }


    private void closeTasksOfGroup(TaskGroupEntity taskGroup, TaskStateTypeEntity taskStateTypeCloseSuccess,
                                   TaskStateTypeEntity taskStateTypeCloseFail,
                                   TaskSubStateTypeEntity subStateTypeEntityAgencyFail,
                                   TaskSubStateTypeEntity subStateTypeEntityDigitalFail,
                                   List<TaskEntity> activeTasks) {
        for (var activeTask : activeTasks) {
            try {

                var taskAgencyNo = activeTask.getAgencyNo();

                var taskStateInformation = activeTask.getTaskStateInformation();

                Optional<AsPolicySaleEntity> taskSaleOpt = getAsSaleRecordForCmTask(taskGroup, activeTask);

                if (taskSaleOpt.isPresent()) {
                    var taskSale = taskSaleOpt.get();

                    log.info(String.format(" Task Group task.ıd:%s task.agency:%s task.owner.agency:%s task.customer.name:%s"
                            ,activeTask.getId(),activeTask.getAgencyNo(),taskSale.getPolicyOwnerAgencyNo(),activeTask.getCustomerNo()));

                    var recordState = taskSale.getPolicyOwnerAgencyNo().equals(taskAgencyNo)
                            ?
                            taskStateTypeCloseSuccess
                            :
                            taskStateTypeCloseFail;

                    var subTaskState = recordState == taskStateTypeCloseFail ?
                            taskManagementSchedulerConfigurationProperties
                                    .getDigitalAgencies().contains(taskSale.getPolicyOwnerAgencyNo()) ? subStateTypeEntityDigitalFail
                                    : subStateTypeEntityAgencyFail
                            : null;


                    log.info(String.format(" Task Group task.ıd:%s task.customer.name:%s task.state.name:%s task.sub.state.name:%s "
                            ,activeTask.getId(),activeTask.getCustomerNo(),recordState.getName(),subTaskState!=null?subTaskState.getName():""));

                    taskStateInformation.setTaskStateType(recordState);
                    taskStateInformation.setTaskSubStateType(subTaskState);
                    taskStateInformationJpaRepository.save(taskStateInformation);

                    saveActiveTask(taskGroup, activeTask, taskStateInformation, taskSale);
                    asPolicySaleJpaRepository.save(taskSale);

                    addHistoryRecord(activeTask, taskStateInformation, recordState);


                }
            } catch (Exception e) {
                log.info(String.format("Task close exception cm_task_id: %s exception message :%s", activeTask.getId(),e.getMessage()));
            }


        }
    }

    private void saveActiveTask(TaskGroupEntity taskGroup, TaskEntity activeTask, TaskStateInformationEntity taskStateInformation, AsPolicySaleEntity taskSale) {

        activeTask.setPolicyNumber(taskSale.getPolicyNumber());
        activeTask.setTaskStateInformation(taskStateInformation);
        activeTask.setAsPolicySaleId(taskSale.getId());
        activeTask.setPolicyAgencyNo(taskSale.getPolicyOwnerAgencyNo());
        activeTask.setMsuName(taskSale.getMsu());
        activeTask.setProposalNumber(taskSale.getProposalNumber());
        activeTask.setTaskGroupId(taskGroup.getId());
        activeTask.setPolicyType(taskSale.getPolicyType());
        activeTask.setSaleChannel(taskSale.getSalesChannel());
        activeTask.setCustomerNo(taskSale.getCustomerNo());
        activeTask.setSaleDate(taskSale.getSaleDate());
        activeTask.setSubjectValue(taskSale.getSubjectValue());
        activeTask.setSubjectType(taskSale.getSubjectType());
        activeTask.setDistributionChannel(taskSale.getDistributionChannel());
        activeTask.setProductionDepartment(taskSale.getProductionDirectorate());
        activeTask.setSks(taskSale.getSks());
        activeTask.setPolicyPremium(taskSale.getPolicyPremium());

        taskJpaRepository.save(activeTask);
    }


    private Optional<AsPolicySaleEntity> getAsSaleRecordForCmTask(TaskGroupEntity taskGroup, TaskEntity task) {


        var knimeTaskId = task.getKnimeTaskId();

        var asRobotTasks = asRobotPolicyProposalJpaRepository.findByIsActiveTrueAndKnimeTaskId(knimeTaskId);

        if (!asRobotTasks.isEmpty()) {

            return getAsPolicySaleEntity(asRobotTasks);
        } else {

            return getAsPolicySaleEntity(taskGroup, task);
        }


    }

    private Optional<AsPolicySaleEntity> getAsPolicySaleEntity(List<AsRobotPolicyProposalEntity> asRobotTasks) {
        for (var asRobotTask : asRobotTasks) {
            var saleEntityOptional = asPolicySaleJpaRepository.findByProposalNumber(asRobotTask.getProposalNumber().trim());
            if (saleEntityOptional.isPresent()) {
                log.info(String.format("AS_ROBOT_SALE_INFO knime_id:%s proposal_no%s", asRobotTask.getKnimeTask().getId(),asRobotTask.getProposalNumber()));

                asRobotTasks = asRobotTasks.stream()
                        .peek(item -> {
                            item.setPolicyNo(saleEntityOptional.get().getPolicyNumber());
                            item.setIsActive(Boolean.FALSE);
                        })
                        .toList();
                asRobotPolicyProposalJpaRepository.saveAll(asRobotTasks);
                return saleEntityOptional;

            }
        }
        return Optional.empty();
    }

    private Optional<AsPolicySaleEntity> getAsPolicySaleEntity(TaskGroupEntity taskGroup, TaskEntity task) {
        var contactNo = task.getCustomerNo();

        var agencyNo = task.getAgencyNo();
        var policyTypes = Arrays.stream(taskGroup.getPolicyNumbers().replace('"', ' ').split(","))
                .map(item -> Long.parseLong(item.trim()))
                .collect(Collectors.toList());

        Optional<AsPolicySaleEntity> taskSaleOpt;

        if (task.getSubjectValue() != null) {

            var taskSales = asPolicySaleJpaRepository
                    .findByCustomerNoAndPolicyTypeInAndSubjectValueAndPolicyOwnerAgencyNoIsNotNullOrderBySaleDateAsc(contactNo, policyTypes, task.getSubjectValue());

            taskSaleOpt = taskSales.stream()
                    .filter(item -> item.getPolicyOwnerAgencyNo().equals(agencyNo))
                    .findFirst();

            if (taskSaleOpt.isEmpty()) {
                taskSaleOpt = asPolicySaleJpaRepository
                        .findTopByCustomerNoAndPolicyTypeInAndSubjectValueAndPolicyOwnerAgencyNoIsNotNullOrderBySaleDateAsc(contactNo, policyTypes, task.getSubjectValue());
            }


        } else {
            var taskSales = asPolicySaleJpaRepository
                    .findByCustomerNoAndPolicyTypeInAndPolicyOwnerAgencyNoIsNotNullOrderBySaleDateAsc(contactNo, policyTypes);

            taskSaleOpt = taskSales.stream()
                    .filter(item -> item.getPolicyOwnerAgencyNo().equals(agencyNo))
                    .findFirst();

            if (taskSaleOpt.isEmpty()) {
                taskSaleOpt = asPolicySaleJpaRepository
                        .findTopByCustomerNoAndPolicyTypeInAndPolicyOwnerAgencyNoIsNotNullOrderBySaleDateAsc(contactNo, policyTypes);
            }


        }

        return taskSaleOpt;
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
