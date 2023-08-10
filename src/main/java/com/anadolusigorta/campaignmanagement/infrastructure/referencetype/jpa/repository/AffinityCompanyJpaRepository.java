package com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.entity.AffinityCompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AffinityCompanyJpaRepository extends JpaRepository<AffinityCompanyEntity, Long> {
}
