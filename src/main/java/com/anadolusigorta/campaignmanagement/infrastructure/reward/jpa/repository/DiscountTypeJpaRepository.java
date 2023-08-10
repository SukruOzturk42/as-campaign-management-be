package com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.DiscountTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountTypeJpaRepository extends JpaRepository<DiscountTypeEntity, Long> {
}
