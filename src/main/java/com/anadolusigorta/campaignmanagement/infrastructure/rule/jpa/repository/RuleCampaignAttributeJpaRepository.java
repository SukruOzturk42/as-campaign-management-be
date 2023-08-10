package com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.entity.RuleCampaignAttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleCampaignAttributeJpaRepository extends JpaRepository<RuleCampaignAttributeEntity, Long> {
}
