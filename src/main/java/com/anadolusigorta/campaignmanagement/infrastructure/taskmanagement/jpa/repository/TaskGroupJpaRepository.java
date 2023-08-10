package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.TaskGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskGroupJpaRepository extends JpaRepository<TaskGroupEntity, Long> {

    List<TaskGroupEntity> findByStartDateBeforeAndEndDateIsAfter(LocalDateTime start,LocalDateTime end);

    List<TaskGroupEntity> findByStartDateAfterAndEndDateIsBefore(LocalDateTime start,LocalDateTime end);

    List<TaskGroupEntity> findByEndDateAfter(LocalDateTime end);

    TaskGroupEntity findByTaskTypeId(Long id);

    TaskGroupEntity findByTaskTypeIdAndEndDateAfter(Long id, LocalDateTime current);

}
