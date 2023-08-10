package com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity.CodeKindEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodeKindJpaRepository extends JpaRepository<CodeKindEntity, Long> {

    Optional<CodeKindEntity> findByName(String name);

}
