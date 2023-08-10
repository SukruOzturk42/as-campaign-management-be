package com.anadolusigorta.campaignmanagement.domain.taskmanagement.facade;

import com.anadolusigorta.campaignmanagement.domain.campaign.port.CampaignRuleGroupRepository;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.sale.model.CreateNotifySaleCampaign;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.*;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.port.CmTaskRepository;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.port.TaskGroupRepository;
import com.anadolusigorta.campaignmanagement.domain.user.facade.UserSecurityFacade;
import com.anadolusigorta.campaignmanagement.domain.user.model.User;
import com.anadolusigorta.campaignmanagement.domain.user.port.UserRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.common.configuration.scheduler.taskmanagement.TaskManagementSchedulerConfigurationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CmTaskFacade {

    private final CmTaskRepository taskRepository;
    private final UserSecurityFacade userSecurityFacade;
    private final UserRepository userRepository;
    private final CampaignRuleGroupRepository ruleGroupRepository;
    private final TaskGroupRepository taskGroupRepository;


    private final TaskManagementSchedulerConfigurationProperties taskManagementSchedulerConfigurationProperties;

    public PageContent<CmTask> getByCriteria(TaskCriteria criteria, Pageable pageable) {
        setDefaultCriteria(criteria);
        var tasks = taskRepository.findByCriteria(criteria,pageable);
        tasks.getContent()
                .forEach(item ->
                        {
                            item.setIsAgencyUserAuth(agencyUserHasNoAuthTaskStateInformation(item));
                            if(!item.getIsAgencyUserAuth()){
                                item.getTaskStateInformation().setTaskSubState(null);
                            }
                        }
                );
        return tasks;
    }

    private Boolean agencyUserHasNoAuthTaskStateInformation(CmTask task){

        if(userSecurityFacade.getUserRole().getName().equals(Constants.AGENCY_USER) &&
                (task.getTaskStateInformation().getTaskState().getName().equals(Constants.TASK_STATE_CLOSED_FAIL)) &&
                (task.getTaskStateInformation().getTaskSubState().getName().equals(Constants.TASK_SUB_STATE_CLOSED_FAIL_AGENCY) ||
                        task.getTaskStateInformation().getTaskSubState().getName().equals(Constants.TASK_SUB_STATE_CLOSED_FAIL_DIGITAL_AGENCY))){
            return Boolean.FALSE;
        }

        return Boolean.TRUE;

    }

    private void setDefaultCriteria(TaskCriteria criteria) {
        if(criteria.getAgencyCodes() == null || criteria.getAgencyCodes().isEmpty()){
            criteria.setAgencyCodes(getRoleTaskAgencies());
        }
        if(criteria.getTaskTypes() == null || criteria.getTaskTypes().isEmpty()){
            var taskGroupTypeIds= taskGroupRepository.getTaskGroupTaskTypes().stream()
                    .map(TaskType::getId)
                    .toList();
            criteria.setTaskTypes(taskGroupTypeIds);
        }
        if(criteria.getRegionCodes() == null || criteria.getRegionCodes().isEmpty()){
            criteria.setRegionCodes(getRegionCodesForCriteria());
        }
    }

    private List<Long> getRegionCodesForCriteria() {
        var role = userSecurityFacade.getUserRole().getName();

        switch (role) {
            case Constants.BSM_USER:
                return taskRepository.findRegionCodeByAgencyNoStartingWith("11");
            case Constants.MSU_USER:
                var employeeNumber = userSecurityFacade.getEmployeeNumber();
                return taskRepository.findRegionCodeByEmployeeNumber(employeeNumber);
            case Constants.REGIONAL_USER_NAME:
                var regionCode = Long.valueOf(userSecurityFacade.getUserRegionCode());
                var regionList = new ArrayList<Long>();
                regionList.add(regionCode);
                return regionList;
            case Constants.AGENCY_USER:
                var agencyCode = userSecurityFacade.getAgencyCode();
                return taskRepository.findRegionCodeByAgencyCode(agencyCode);
            default:
                return null;
        }
    }

    public List<String> getRoleTaskAgency() {
        var role = userSecurityFacade.getUserRole().getName();

        switch (role) {
            case Constants.BSM_USER:

                return taskRepository.findAgencyCodeByAgencyNoStartingWith("11");
            case Constants.REGIONAL_USER_NAME:
                var regionCode = userSecurityFacade.getUserRegionCode();
                return taskRepository.findAgencyNoByAgencyRegionCode(Long.valueOf(regionCode));
            case Constants.AGENCY_USER:

                var agencyCode = userSecurityFacade.getAgencyCode();
                var list = new ArrayList<String>();
                list.add(agencyCode);
                return list;
            default:
                return taskRepository.findAllAgencyNo();
        }
    }

    public List<String> getRoleTaskAgencies() {
        var role = userSecurityFacade.getUserRole().getName();

        switch (role) {
            case Constants.BSM_USER:

                return taskRepository.findAgencyCodeByAgencyNoStartingWith("11");
            case Constants.REGIONAL_USER_NAME:
                var regionCode = userSecurityFacade.getUserRegionCode();
                return taskRepository.findAgencyNoByAgencyRegionCode(Long.valueOf(regionCode));
            case Constants.AGENCY_USER:

                var agencyCode = userSecurityFacade.getAgencyCode();
                var list = new ArrayList<String>();
                list.add(agencyCode);
                return list;
            default:
                return null;
        }
    }

    public Set<TaskType> getAllTaskType() {
        return taskGroupRepository.getTaskGroupTaskTypes();
    }

    public List<TaskType> getAllTaskTypeByTaskGroup() {
        return taskRepository.findAllTaskTypeByTaskGroup();
    }

    public List<User> getTaskAgencyUsers(String agencyCode) {
        var userRole = userSecurityFacade.getUserRole();

        return userRepository.findUsersByAgencyCode(agencyCode);
    }

    public CmTask saveTaskOwner(Long taskId, String taskOwnerName, String taskOwnerUserName) {
        return taskRepository.saveTaskOwner(taskId, taskOwnerName, taskOwnerUserName);
    }

    public void closeContactTaskAfterSoldPolicy(CreateNotifySaleCampaign createNotifySaleCampaign) {

        var ruleGroup = ruleGroupRepository.findById(createNotifySaleCampaign.getRuleGroupId());
        var taskGroup = ruleGroup.getTaskGroup();

        if (taskGroup != null) {
            var soldDetail = createNotifySaleCampaign.getSoldPolicyDetail();
            var policyType = soldDetail.getPolicyType();
            var optionalCmTask = taskRepository
                    .findTopActiveContactTaskByTaskGroupIdAndPolicyType(createNotifySaleCampaign.getContactNumber(), taskGroup.getId(), policyType);
            if (optionalCmTask.isPresent()) {
                var cmTask = optionalCmTask.get();
                cmTask.setRenewalNumber(soldDetail.getRevisionNumber());
                cmTask.setPolicyNumber(soldDetail.getPolicyNumber());
                taskRepository.closeTaskAfterSaleOperation(cmTask);
            }
        }


    }

    public List<TaskSuccessRate> getSuccessRateByRole(SuccessRateCriteria successRateCriteria) {

        var startDay = successRateCriteria.getFirst().toLocalDate().atStartOfDay();
        var endDay = successRateCriteria.getLast().toLocalDate().atTime(LocalTime.MAX);

        var taskGroups = taskGroupRepository
                .getAllTaskGroupsByDates(startDay, endDay);

        List<TaskSuccessRate> successRates = new ArrayList<>();

        for (var taskGroup : taskGroups) {
            var taskSuccessModal = new TaskSuccessRate();

            List<String> taskCriteria = successRateCriteria.getTaskCriteria();

            switch (successRateCriteria.getRole()) {
                case Constants.BSM_USER -> {
                    taskSuccessModal.setTotalTasks(taskRepository
                            .countByAgencyNoStartingWithAndTaskGroupId("11", taskGroup.getId()));
                    taskSuccessModal.setSuccessTasks(taskRepository
                            .countByAgencyNoStartingWithAndTaskStateInformationTaskStateTypeNameAndTaskGroupId("11",
                                    Constants.TASK_STATE_CLOSED_SUCCESS, taskGroup.getId()));
                }
                case Constants.MSU_USER -> {
                    taskSuccessModal.setTotalTasks(taskRepository.msuCountByTaskGroupId(taskGroup.getId()));
                    taskSuccessModal.setSuccessTasks(taskRepository
                            .msuCountByTaskStateInformationTaskStateTypeNameAndTaskGroupId(
                                    Constants.TASK_STATE_CLOSED_SUCCESS, taskGroup.getId()));
                }
                case Constants.REGIONAL_USER_NAME -> {
                    var regionCodes=taskCriteria.stream().map(Long::valueOf).toList();
                    taskSuccessModal.setTotalTasks(taskRepository
                            .countByAgencyRegionCodeInAndTaskGroupId(regionCodes, taskGroup.getId()));
                    taskSuccessModal.setSuccessTasks(taskRepository
                            .countByAgencyRegionCodeInAndTaskStateInformationTaskStateTypeNameAndTaskGroupId(regionCodes,
                                    Constants.TASK_STATE_CLOSED_SUCCESS, taskGroup.getId()));
                }
                case Constants.AGENCY_USER -> {
                    taskSuccessModal.setTotalTasks(taskRepository
                            .countByAgencyNoInAndTaskGroupId(taskCriteria, taskGroup.getId()));
                    taskSuccessModal.setSuccessTasks(taskRepository
                            .countByAgencyNoInAndTaskStateInformationTaskStateTypeNameAndTaskGroupId(taskCriteria,
                                    Constants.TASK_STATE_CLOSED_SUCCESS, taskGroup.getId()));
                }
                default -> {
                    taskSuccessModal.setTotalTasks(taskRepository
                            .countAllByTaskGroupId(taskGroup.getId()));
                    taskSuccessModal.setSuccessTasks(taskRepository
                            .companySuccessCountAllByTaskGroupId(taskGroup.getId()));
                }
            }
            taskSuccessModal.setTaskListName(taskGroup.getName());
            taskSuccessModal.setTaskTypeName(taskGroup.getTaskType().getName());
            taskSuccessModal.setGoal(taskGroup.getGoal());
            taskSuccessModal.setRatio(taskRepository.calculateSuccessRate(taskSuccessModal));
            successRates.add(taskSuccessModal);

        }
        return successRates;
    }


    public List<Long> getRegionCodes() {
        var role = userSecurityFacade.getUserRole().getName();

        switch (role) {
            case Constants.BSM_USER -> {
                return taskRepository.findRegionCodeByAgencyNoStartingWith("11");
            }
            case Constants.MSU_USER -> {
                var employeeNumber = userSecurityFacade.getEmployeeNumber();
                return taskRepository.findRegionCodeByEmployeeNumber(employeeNumber);
            }
            case Constants.REGIONAL_USER_NAME -> {
                var regionCode = Long.valueOf(userSecurityFacade.getUserRegionCode());
                var regionList = new ArrayList<Long>();
                regionList.add(regionCode);
                return regionList;
            }
            case Constants.AGENCY_USER -> {
                var agencyCode = userSecurityFacade.getAgencyCode();
                return taskRepository.findRegionCodeByAgencyCode(agencyCode);
            }
            default -> {
                return taskRepository.findAllRegionCodes();
            }
        }
    }

    public List<String> getAgencyUserAgencies() {
        return taskRepository.findAllAgencyUsersAgencyCodes("11%",
                taskManagementSchedulerConfigurationProperties.getDigitalAgencies());
    }
}
