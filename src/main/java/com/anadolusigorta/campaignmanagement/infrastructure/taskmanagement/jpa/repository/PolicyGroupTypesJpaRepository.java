package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.PolicyGroupEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.PolicyGroupTypesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyGroupTypesJpaRepository extends JpaRepository<PolicyGroupTypesEntity, Long> {
}
