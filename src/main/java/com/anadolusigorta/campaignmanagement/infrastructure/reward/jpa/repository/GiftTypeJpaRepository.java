package com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.GiftTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftTypeJpaRepository extends JpaRepository<GiftTypeEntity, Long> {
}
