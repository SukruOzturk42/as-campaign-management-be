package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.TaskStateTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskStateTypeJpaRepository extends JpaRepository<TaskStateTypeEntity, Long> {

    Optional<TaskStateTypeEntity> findByName(String name);

    List<TaskStateTypeEntity> findByNameIn(List<String> names);

    Optional<List<TaskStateTypeEntity>> findByIdIn(List<Long> id);

    TaskStateTypeEntity findByIdAndTaskSubStateTypesAccessRoleCodesContaining(Long id, String roleCode);

}
