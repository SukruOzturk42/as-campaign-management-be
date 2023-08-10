package com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.GiftPaymentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftPaymentTypeJpaRepository extends JpaRepository<GiftPaymentTypeEntity, Long> {
}
