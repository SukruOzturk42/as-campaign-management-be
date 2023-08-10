package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.TaskStateInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStateInformationJpaRepository extends JpaRepository<TaskStateInformationEntity, Long> {

}
