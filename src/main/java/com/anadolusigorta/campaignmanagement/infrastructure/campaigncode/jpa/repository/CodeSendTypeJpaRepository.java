package com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity.CodeSendTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeSendTypeJpaRepository extends JpaRepository<CodeSendTypeEntity, Long> {
}
