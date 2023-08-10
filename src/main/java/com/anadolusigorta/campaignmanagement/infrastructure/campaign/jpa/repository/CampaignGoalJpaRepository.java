package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignGoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignGoalJpaRepository extends JpaRepository<CampaignGoalEntity, Long> {

    List<CampaignGoalEntity> findAllByCampaignId(Long campaignId);

}
