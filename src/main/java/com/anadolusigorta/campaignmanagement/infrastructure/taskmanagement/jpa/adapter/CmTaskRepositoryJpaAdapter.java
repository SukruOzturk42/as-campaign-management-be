package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.saletask.model.Agency;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.*;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.port.CmTaskRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.Specification;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.CmTaskProcessHistoryEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.KnimeTaskTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.TaskEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.TaskStateTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.repository.*;
import com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.adapter.UserRepositoryJpaAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CmTaskRepositoryJpaAdapter implements CmTaskRepository {

    private final TaskJpaRepository taskJpaRepository;
    private final KnimeTaskTypeJpaRepository knimeTaskTypeJpaRepository;
    private final TaskGroupJpaRepository taskGroupJpaRepository;
    private final TaskStateInformationJpaRepository taskStateInformationJpaRepository;
    private final TaskStateTypeJpaRepository taskStateTypeJpaRepository;
    private final UserRepositoryJpaAdapter userRepositoryJpaAdapter;
    private final CmTaskProcessHistoryRepository cmTaskProcessHistoryRepository;

    public static Specification<TaskEntity> matchedCriteria(TaskCriteria criteria) {
        return (root, query, builder) -> {

            List<Predicate> predicates = new ArrayList<>();
            final Path<Long> taskGroup = getPath(Long.class, root, "taskGroupId");
            predicates.add(taskGroup.isNotNull());
            if (criteria.getAgencyCodes() != null && !criteria.getAgencyCodes().isEmpty() &&
                    criteria.getAgencyCodes().size() > 1000) {
                Predicate predicate = null;
                for (int i = 0; i < criteria.getAgencyCodes().size(); i += 999) {
                    List subList ;
                    if (criteria.getAgencyCodes().size() > i + 999) {
                        subList = criteria.getAgencyCodes().subList(i, (i + 999));
                    } else {
                        subList = criteria.getAgencyCodes().subList(i, criteria.getAgencyCodes().size());
                    }
                    final Path<String> group = getPath(String.class, root, "agencyNo");
                    if (predicate == null) {
                        predicate = group.in(subList);
                    } else {
                        predicate = builder.or(predicate,group.in(subList));
                    }
                }
                predicates.add(predicate);
            }
            else if (criteria.getAgencyCodes() != null && !criteria.getAgencyCodes().isEmpty()) {
                final Path<String> group = getPath(String.class, root, "agencyNo");
                predicates.add(group.in(criteria.getAgencyCodes()));
            }

            if (criteria.getRegionCodes() != null && !criteria.getRegionCodes().isEmpty()) {
                final Path<Long> group = getPath(Long.class, root, "regionCode");
                predicates.add(group.in(criteria.getRegionCodes()));
            }

            if (criteria.getTaskTypes() != null && !criteria.getTaskTypes().isEmpty()) {
                final Path<Long> group = getPath(Long.class, root, "taskType.id");
                predicates.add(group.in(criteria.getTaskTypes()));
            }

            if (criteria.getTaskStateTypes() != null && !criteria.getTaskStateTypes().isEmpty()) {
                final Path<Long> group = getPath(Long.class, root, "taskStateInformation.taskStateType.id");
                predicates.add(group.in(criteria.getTaskStateTypes()));
            }

            if (criteria.getTaskStateTypes() != null && !criteria.getTaskStateTypes().isEmpty() &&
                    criteria.getTaskSubStateTypes() != null && !criteria.getTaskSubStateTypes().isEmpty()) {
                final Path<Long> group = getPath(Long.class, root, "taskStateInformation.taskSubStateType.id");
                predicates.add(group.in(criteria.getTaskSubStateTypes()));
            }

            if (criteria.getTaskId() != null) {
                final Path<Long> group = getPath(Long.class, root, "id");
                predicates.add(builder.equal(group,criteria.getTaskId()));
            }

            if (criteria.getContactNumber() != null && !Objects.equals(criteria.getContactNumber(), "")) {
                final Path<String> group = getPath(String.class, root, "customerNo");
                predicates.add(builder.like(group, criteria.getContactNumber() + "%"));
            }

            if (criteria.getPeriodStartDate() != null) {
                criteria.setPeriodStartDate(
                        criteria.getPeriodStartDate().toLocalDate().atTime(0, 0, 0)
                );
                final Path<LocalDateTime> group = getPath(LocalDateTime.class, root, "periodStartDate");
                predicates.add(builder.greaterThanOrEqualTo(group,criteria.getPeriodStartDate()));
            }

            if (criteria.getExpireDate() != null) {
                criteria.setExpireDate(
                        criteria.getExpireDate().toLocalDate().atTime(23, 59, 59)
                );
                final Path<LocalDateTime> group = getPath(LocalDateTime.class, root, "expireDate");
                predicates.add(builder.lessThanOrEqualTo(group,criteria.getExpireDate()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static <T, R> Path<R> getPath(Class<R> resultType, Path<T> root, String path) {
        String[] pathElements = path.split("\\.");
        Path<?> retVal = root;
        for (String pathEl : pathElements) {
            retVal = (Path<R>) retVal.get(pathEl);
        }
        return (Path<R>) retVal;
    }


    @Override
    public PageContent<CmTask> findByCriteria(TaskCriteria criteria, Pageable pageable) {

        var cmTaskList = taskJpaRepository.findAll(matchedCriteria(criteria),
                PageRequest.of(pageable.getPageNumber() - 1
                        , pageable.getPageSize()
                        , pageable.getSort()));
        return PageContent.<CmTask>builder()
                .content(cmTaskList.getContent().stream()
                        .map(TaskEntity::toModel)
                        .collect(Collectors.toList()))
                .page(pageable.getPageNumber())
                .size(pageable.getPageSize())
                .totalItems(cmTaskList.getTotalElements())
                .build();
    }

    @Override
    public List<String> findAgencyCode(String roleName) {


        return null;
    }

    @Override
    public List<String> findAgencyCodeByAgencyNoStartingWith(String agencyNo) {
        return taskJpaRepository.findAgencyNoByAgencyNoStartingWith(agencyNo)
                .stream()
                .map(TaskEntity::getAgencyNo)
                .filter(Objects::nonNull)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAgencyNoByAgencyRegionCode(Long regionCode) {
        return taskJpaRepository.findAgencyNoByRegionCode(regionCode)
                .stream()
                .map(TaskEntity::getAgencyNo)
                .filter(Objects::nonNull)
                .filter(i -> !i.startsWith("11"))
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllAgencyNo() {
        return taskJpaRepository.findAllAgencyCodes();
    }

    @Override
    public List<TaskType> findAllTaskTypeByTaskGroup() {
        var types = knimeTaskTypeJpaRepository.findAll()
                .stream()
                .map(KnimeTaskTypeEntity::toModel)
                .toList();

        return types.stream()
                .filter(i -> taskGroupJpaRepository
                        .findByTaskTypeIdAndEndDateAfter(i.getId(), LocalDateTime.now()) == null)
                .toList();
    }

    @Override
    public List<TaskType> findAllTaskType() {
        return knimeTaskTypeJpaRepository.findAll()
                .stream()
                .map(KnimeTaskTypeEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public CmTask saveTaskOwner(Long taskId, String taskOwnerName, String taskOwnerUserName) {
        var task = taskJpaRepository.findById(taskId)
                .orElseThrow(() -> new CampaignManagementException("task.not.found"));
        var taskStateType = taskStateTypeJpaRepository.findByName(Constants.TASK_STATE_ON_PROGRESS)
                .orElseThrow(() -> new CampaignManagementException("task.state.type.not.found"));
        task.setTaskOwnerName(taskOwnerName);
        task.setTaskOwnerUserName(taskOwnerUserName);

        saveHistory(task, taskStateType);
        return taskJpaRepository.save(task).toModel();
    }

    private void saveHistory(TaskEntity taskEntity, TaskStateTypeEntity taskStateTypeEntity) {

        var taskProcessHistory = new CmTaskProcessHistoryEntity();

        var user = userRepositoryJpaAdapter.getUserFromContext();

        taskProcessHistory.setTaskId(taskEntity.getId());
        taskProcessHistory.setProcessDate(LocalDateTime.now());
        taskProcessHistory.setTaskStateType(taskStateTypeEntity);
        taskProcessHistory.setUsername(user.getUsername());
        taskProcessHistory.setName(user.getFullName());
        taskProcessHistory.setDesc(Constants.TASK_OWNER_CHANGED
                + taskEntity.getTaskOwnerName());
        cmTaskProcessHistoryRepository.save(taskProcessHistory);

    }

    @Override
    public boolean hasContactTasks(String contactNo, Long knimeTaskTypeId) {
        var contactTasks = taskJpaRepository.findByCustomerNoAndTaskTypeId(contactNo, knimeTaskTypeId);
        return !contactTasks.isEmpty();
    }

    @Override
    public Optional<CmTask> findTopActiveContactTaskByTaskGroupIdAndPolicyType(String contactNo, Long taskGroupId, String policyType) {
        var taskGroup = taskGroupJpaRepository.findById(taskGroupId)
                .orElseThrow(() -> new CampaignManagementException("task.group.not.found"));

        var taskStateNames = new ArrayList<String>();
        taskStateNames.add(Constants.TASK_STATE_OPEN);
        taskStateNames.add(Constants.TASK_STATE_ON_PROGRESS);

        var cmTask = taskJpaRepository
                .findTopByCustomerNoAndTaskTypeIdAndPolicyTypeAndTaskStateInformationTaskStateTypeNameIn(contactNo
                        , taskGroup.getTaskType().getId(), Long.valueOf(policyType)
                        , taskStateNames);

        return cmTask.map(TaskEntity::toModel);
    }

    @Override
    public void closeTaskAfterSaleOperation(CmTask cmTask) {
        var stateInformation = cmTask.getTaskStateInformation();
        var stateInformationEntity = taskStateInformationJpaRepository.findById(stateInformation.getId())
                .orElseThrow(() -> new CampaignManagementException("task.state.not.found"));
        var closeState = taskStateTypeJpaRepository.findByName(Constants.TASK_STATE_CLOSED_SUCCESS)
                .orElseThrow(() -> new CampaignManagementException("task.state.type.not.found"));
        stateInformationEntity.setTaskStateType(closeState);
        stateInformationEntity.setNote("Asos satışı sonucu task otomatik kapanmıştır");
        taskStateInformationJpaRepository.save(stateInformationEntity);

        var cmTaskEntity = taskJpaRepository.findById(cmTask.getId())
                .orElseThrow(() -> new CampaignManagementException("task.not.found"));
        cmTaskEntity.setTaskStateInformation(stateInformationEntity);
        cmTaskEntity.setPolicyNumber(cmTask.getPolicyNumber());
        cmTaskEntity.setRenewalNumber(cmTask.getRenewalNumber());
        taskJpaRepository.save(cmTaskEntity);

    }


    @Override
    public List<Long> findRegionCodeByEmployeeNumber(String employeeNumber) {
        return taskJpaRepository.findAllRegionCodes();
    }

    @Override
    public List<Long> findAllRegionCodes() {
        return taskJpaRepository.findAllRegionCodes()
                .stream().distinct()
                .collect(Collectors.toList());
    }

    @Override
    public Long countByAgencyNoStartingWithAndTaskGroupId(String s, Long id) {
        return taskJpaRepository.countByAgencyNoStartingWithAndTaskGroupId(s, id);
    }

    @Override
    public Long countByAgencyNoStartingWithAndTaskStateInformationTaskStateTypeNameAndTaskGroupId(String s, String type, Long id) {
        return taskJpaRepository.countByAgencyNoStartingWithAndTaskStateInformationTaskStateTypeNameAndTaskGroupId(s, type, id);
    }

    @Override
    public Long msuCountByTaskGroupId(Long id) {
        return taskJpaRepository.countByTaskGroupIdAndMsuNameIsNotNull(id);
    }

    @Override
    public List<String> findAgencyCodeByMsuNameIsNotNull() {
        return taskJpaRepository.findAgencyNoByMsuNameNotNull();
    }

    @Override
    public Long countByAgencyRegionCodeInAndTaskGroupId(List<Long> taskCriteria, Long id) {
        return taskJpaRepository.countByRegionCodeInAndTaskGroupId(taskCriteria, id);
    }

    @Override
    public Long countByAgencyNoInAndTaskGroupId(List<String> taskCriteria, Long id) {
        List<List<String>> agencyNoPartitions = ListUtils.partition(taskCriteria, 999);
        if (!CollectionUtils.isEmpty(taskCriteria) && taskCriteria.size() > 1000) {
            Long taskCount = 0L;
            for (List<String> partition : agencyNoPartitions) {
                taskCount += taskJpaRepository.countByAgencyNoInAndTaskGroupId(partition, id);
            }
            return taskCount;
        }
        return taskJpaRepository.countByAgencyNoInAndTaskGroupId(taskCriteria, id);
    }

    @Override
    public Long msuCountByTaskStateInformationTaskStateTypeNameAndTaskGroupId(String stateName, Long taskGroupId) {
        return taskJpaRepository
                .countByTaskGroupIdAndTaskStateInformationTaskStateTypeNameAndMsuNameIsNotNull(taskGroupId,stateName);
    }

    @Override
    public Long countAllByTaskGroupId(Long id) {
        return taskJpaRepository.countAllByTaskGroupId(id);
    }

    @Override
    public Long companySuccessCountAllByTaskGroupId( Long id) {
        return taskJpaRepository.countAllByTaskGroupIdAndPolicyNumberIsNotNull(id);
    }

    @Override
    public Long countByAgencyRegionCodeInAndTaskStateInformationTaskStateTypeNameAndTaskGroupId(List<Long> regionCodes, String type, Long taskGroupId) {
        return taskJpaRepository.countByRegionCodeInAndTaskStateInformationTaskStateTypeNameAndTaskGroupId(regionCodes, type, taskGroupId);
    }

    @Override
    public Long countByAgencyNoInAndTaskStateInformationTaskStateTypeNameAndTaskGroupId(List<String> agencyNos, String type, Long taskGroupId) {
        List<List<String>> agencyNoPartitions = ListUtils.partition(agencyNos, 999);
        if (!CollectionUtils.isEmpty(agencyNos) && agencyNos.size() > 1000) {
            Long taskCount = 0L;
            for (List<String> partition : agencyNoPartitions) {
                taskCount+=taskJpaRepository.countByAgencyNoInAndTaskStateInformationTaskStateTypeNameAndTaskGroupId(partition, type, taskGroupId);
            }
            return taskCount;
        }
        return taskJpaRepository.countByAgencyNoInAndTaskStateInformationTaskStateTypeNameAndTaskGroupId(agencyNos, type, taskGroupId);
    }

    @Override
    public String calculateSuccessRate(TaskSuccessRate taskSuccessRate) {
        if (taskSuccessRate.getTotalTasks() != null && taskSuccessRate.getTotalTasks() != 0L)
            return String.format("%.2f", (100.0 * taskSuccessRate.getSuccessTasks()) / taskSuccessRate.getTotalTasks());
        else
            return "0.0";
    }

    @Override
    public List<String> findAllAgencyUsersAgencyCodes(String s, List<String> digitalAgencies) {
        return taskJpaRepository.findAllAgencyUsersAgencyCodes(s, digitalAgencies);
    }

    @Override
    public List<Long> findRegionCodeByAgencyNoStartingWith(String s) {
        return taskJpaRepository.findAgencyRegionCodeByAgencyNoStartingWith(s)
                .stream()
                .map(TaskEntity::getRegionCode)
                .filter(Objects::nonNull)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> findRegionCodeByAgencyCode(String agencyCode) {
        return taskJpaRepository.findRegionCodeByAgencyNo(agencyCode)
                .stream()
                .map(TaskEntity::getRegionCode)
                .filter(Objects::nonNull)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}
