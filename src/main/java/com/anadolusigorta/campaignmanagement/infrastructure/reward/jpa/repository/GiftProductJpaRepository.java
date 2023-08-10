package com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.RewardGiftProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftProductJpaRepository extends JpaRepository<RewardGiftProductEntity, Long> {
}
