package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository;


import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignRuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignRuleJpaRepository extends JpaRepository<CampaignRuleEntity, Long> {

    List<CampaignRuleEntity> findAll();

    List<CampaignRuleEntity> findAllByRuleId(Long id);

    List<CampaignRuleEntity> findByOwnerProductId(Long ownerProductId);

    List<CampaignRuleEntity> findAByCampaignAttributeId(Long campaignAttributeId);

}
