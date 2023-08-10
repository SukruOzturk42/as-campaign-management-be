package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignReasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignReasonJpaRepository extends JpaRepository<CampaignReasonEntity, Long> {


    List<CampaignReasonEntity> findAllByCampaignId(Long campaignId);



}
