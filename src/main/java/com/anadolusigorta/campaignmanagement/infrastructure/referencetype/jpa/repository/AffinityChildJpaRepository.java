package com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.entity.AffinityChildEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AffinityChildJpaRepository extends JpaRepository<AffinityChildEntity, Long> {
}
