package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.TaskStateTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.TaskSubStateTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskSubStateTypeJpaRepository extends JpaRepository<TaskSubStateTypeEntity, Long> {

    Optional<TaskSubStateTypeEntity> findByName(String name);

    List<TaskSubStateTypeEntity> findByNameIn(List<String> names);

}
