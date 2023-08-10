package com.anadolusigorta.campaignmanagement.domain.taskmanagement.port;


import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.CmTask;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskCriteria;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskSuccessRate;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskType;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CmTaskRepository {

    PageContent<CmTask> findByCriteria(TaskCriteria criteria, Pageable pageable);

    List<String> findAgencyCode(String roleName);

    List<String> findAgencyCodeByAgencyNoStartingWith(String agencyNo);


    List<String> findAgencyNoByAgencyRegionCode(Long agencyRegionCode);

    List<String> findAllAgencyNo();

    List<TaskType> findAllTaskTypeByTaskGroup();

    List<TaskType> findAllTaskType();

    CmTask saveTaskOwner(Long taskId, String taskOwnerName,String taskOwnerUserName);

    boolean hasContactTasks(String contactNo,Long knimeTaskTypeId);

    Optional<CmTask> findTopActiveContactTaskByTaskGroupIdAndPolicyType(String contactNo,Long taskGroupId,String policyType);

    void closeTaskAfterSaleOperation(CmTask cmTask);


    List<Long> findRegionCodeByEmployeeNumber(String employeeNumber);

    String calculateSuccessRate(TaskSuccessRate taskSuccessRate);

    List<Long> findAllRegionCodes();


    Long countByAgencyNoStartingWithAndTaskGroupId(String s, Long id);

    Long countByAgencyNoStartingWithAndTaskStateInformationTaskStateTypeNameAndTaskGroupId(String s, String type, Long id);

    List<String> findAgencyCodeByMsuNameIsNotNull();

    Long countByAgencyRegionCodeInAndTaskGroupId(List<Long> taskCriteria, Long id);

    Long countByAgencyNoInAndTaskGroupId(List<String> taskCriteria, Long id);

    Long msuCountByTaskGroupId(Long id);

    Long countAllByTaskGroupId(Long id);

    Long companySuccessCountAllByTaskGroupId(Long id);

    Long countByAgencyNoInAndTaskStateInformationTaskStateTypeNameAndTaskGroupId(List<String> agencyNos, String type, Long taskGroupId);

    Long msuCountByTaskStateInformationTaskStateTypeNameAndTaskGroupId(String type, Long taskGroupId);

    Long countByAgencyRegionCodeInAndTaskStateInformationTaskStateTypeNameAndTaskGroupId(List<Long> regionCodes, String type, Long taskGroupId);

    List<String> findAllAgencyUsersAgencyCodes(String s, List<String> digitalAgencies);

    List<Long> findRegionCodeByAgencyNoStartingWith(String s);

    List<Long> findRegionCodeByAgencyCode(String agencyCode);
}
