package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.TaskEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.TaskStateTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TaskJpaRepository extends JpaRepository<TaskEntity, Long>, JpaSpecificationExecutor<TaskEntity> {

      @Query(value = "Select distinct t.agencyNo From TaskEntity t ORDER BY t.agencyNo")
      List<String> findAllAgencyCodes();

      @Query(value = "Select t From TaskEntity t where t.agencyNo in :agencyNos and t.createdAt between :first and :last")
      List<TaskEntity> findAllAgencyCodesIn(@Param("agencyNos")Collection<String> agencyNos,@Param("first") LocalDateTime first,@Param("last")LocalDateTime last);

      List<TaskEntity> findAgencyNoByRegionCode(Long regionCode);

      List<TaskEntity> findAgencyNoByAgencyNoStartingWith(String agencyNo);

      List<TaskEntity> findAllByTaskStateInformationTaskStateTypeAndUpdatedAtBetween(TaskStateTypeEntity taskStateInformation_taskStateType, LocalDateTime start,LocalDateTime end);


      List<TaskEntity> findByCustomerNoAndTaskTypeId(String customerNo,Long knimeTaskId);

      Optional<TaskEntity> findTopByCustomerNoAndTaskTypeIdAndPolicyTypeAndTaskStateInformationTaskStateTypeNameIn(String customerNo, Long taskType_id, Long policyType, Collection<String> taskStateInformation_taskStateType_name);


      List<TaskEntity> findAllByTaskStateInformationTaskStateTypeInAndExpireDateBefore(List<TaskStateTypeEntity> taskStateInformation_taskStateType, LocalDateTime now);

      List<TaskEntity> findAllByTaskTypeIdAndTaskStateInformationTaskStateTypeInAndExpireDateAfter(Long taskTypeId,List<TaskStateTypeEntity> stateName, LocalDateTime now);

      Optional<TaskEntity> findTopByKnimeTaskId(String id);

      Long countByAgencyNoStartingWithAndTaskGroupId(String agencyNo, Long taskGroupId);

      Long countByRegionCodeInAndTaskGroupId(List<Long> regionCodes, Long taskGroupId);

      Long countByAgencyNoInAndTaskGroupId(List<String> agencyNo, Long taskGroupId);

      Long countByTaskGroupIdAndMsuNameIsNotNull(Long taskGroupId);

      Long countByTaskGroupIdAndTaskStateInformationTaskStateTypeNameAndMsuNameIsNotNull(Long taskGroupId, String stateName);

      Long countByAgencyNoStartingWithAndTaskStateInformationTaskStateTypeNameAndTaskGroupId(String agencyNo, String type, Long taskGroupId);

      Long countByRegionCodeInAndTaskStateInformationTaskStateTypeNameAndTaskGroupId(List<Long> regionCodes, String type, Long taskGroupId);

      Long countAllByTaskGroupId(Long taskGroupId);

      Long countAllByTaskGroupIdAndPolicyNumberIsNotNull(Long taskGroupId);

      Long countByAgencyNoInAndTaskStateInformationTaskStateTypeNameAndTaskGroupId(List<String> agencyNo, String type, Long taskGroupId);

      @Query(value = "Select distinct t.regionCode From TaskEntity t ORDER BY t.regionCode")
      List<Long> findAllRegionCodes();

      @Query(value = "Select distinct t.agencyNo From TaskEntity t Where t.msuName is not null")
      List<String> findAgencyNoByMsuNameNotNull();

      @Query(value = "Select distinct t.agencyNo " +
              "From TaskEntity t " +
              "where t.agencyNo not like :startsWith " +
              "and t.agencyNo not in :digitalAgencies " +
              "ORDER BY t.agencyNo")
      List<String> findAllAgencyUsersAgencyCodes(@Param("startsWith")String startsWith,@Param("digitalAgencies") List<String> digitalAgencies);

      List<TaskEntity> findAgencyRegionCodeByAgencyNoStartingWith(String agencyNo);

      List<TaskEntity> findRegionCodeByAgencyNo(String agencyCode);
}
