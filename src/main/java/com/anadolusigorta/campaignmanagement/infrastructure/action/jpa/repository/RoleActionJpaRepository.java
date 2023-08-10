package com.anadolusigorta.campaignmanagement.infrastructure.action.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.action.jpa.entity.RoleActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleActionJpaRepository extends JpaRepository<RoleActionEntity, Long> {

    Optional<RoleActionEntity> findByCode(Long code);

}
