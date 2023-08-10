package com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.GiftDeliveryTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftDeliveryTypeJpaRepository extends JpaRepository<GiftDeliveryTypeEntity, Long> {
}
