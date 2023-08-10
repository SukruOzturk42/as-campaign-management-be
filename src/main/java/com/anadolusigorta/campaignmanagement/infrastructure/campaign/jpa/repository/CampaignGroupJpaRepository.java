package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignGroupJpaRepository extends JpaRepository<CampaignGroupEntity, Long> {

}
