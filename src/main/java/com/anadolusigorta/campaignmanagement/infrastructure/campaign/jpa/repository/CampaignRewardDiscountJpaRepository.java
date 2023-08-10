package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.RuleGroupRewardDiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRewardDiscountJpaRepository extends JpaRepository<RuleGroupRewardDiscountEntity, Long> {

}
