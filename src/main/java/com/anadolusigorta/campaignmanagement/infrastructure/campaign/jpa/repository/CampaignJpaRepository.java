package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CampaignJpaRepository extends JpaRepository<CampaignEntity, Long> {

}
