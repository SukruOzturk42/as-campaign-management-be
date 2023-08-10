package com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity.CodeTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeTypeJpaRepository extends JpaRepository<CodeTypeEntity, Long> {
}
