package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignDetailJpaRepository extends JpaRepository<CampaignDetailEntity, Long> {

    List<CampaignDetailEntity> findAllByCampaignId(Long campaignId);

    List<CampaignDetailEntity> findAllByCampaignIdAndReferenceTypeName(Long campaignId,String referenceType);

}
