package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.repository;

import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskProcessHistory;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.CmTaskProcessHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CmTaskProcessHistoryRepository extends JpaRepository<CmTaskProcessHistoryEntity, Long> {

    List<CmTaskProcessHistoryEntity> findAllByTaskId(Long taskId);
}
