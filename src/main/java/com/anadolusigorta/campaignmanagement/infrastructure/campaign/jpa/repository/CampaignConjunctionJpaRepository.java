package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignConjunctionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignConjunctionJpaRepository extends JpaRepository<CampaignConjunctionEntity, Long> {

    List<CampaignConjunctionEntity> findCampaignConjunctionEntitiesByCompanyId(Long companyId);

}
