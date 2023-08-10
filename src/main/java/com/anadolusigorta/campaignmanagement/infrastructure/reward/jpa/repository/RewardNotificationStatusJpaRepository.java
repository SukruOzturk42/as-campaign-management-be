package com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.RewardNotificationStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RewardNotificationStatusJpaRepository extends JpaRepository<RewardNotificationStatusEntity, Long> {

    Optional<RewardNotificationStatusEntity> findByCode(String code);
}
