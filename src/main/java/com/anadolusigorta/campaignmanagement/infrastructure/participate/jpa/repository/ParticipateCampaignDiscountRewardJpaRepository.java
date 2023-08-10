package com.anadolusigorta.campaignmanagement.infrastructure.participate.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.participate.jpa.entity.ParticipateCampaignDiscountRewardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ParticipateCampaignDiscountRewardJpaRepository extends JpaRepository<ParticipateCampaignDiscountRewardEntity, Long> {

	List<ParticipateCampaignDiscountRewardEntity>  findAllByIsSentFalseAndRewardSendDateIsBefore(LocalDateTime localDateTime);
}
