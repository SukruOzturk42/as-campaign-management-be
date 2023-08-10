package com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.entity.AffinityParentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AffinityParentJpaRepository extends JpaRepository<AffinityParentEntity, Long> {
}
