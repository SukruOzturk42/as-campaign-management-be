package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.KnimeTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KnimeTaskJpaRepository extends JpaRepository<KnimeTaskEntity, Long> {

    List<KnimeTaskEntity> findByTaskTypeId(Long taskTypeId);

    List<KnimeTaskEntity> findAllByIsTransferredFalse();

}
