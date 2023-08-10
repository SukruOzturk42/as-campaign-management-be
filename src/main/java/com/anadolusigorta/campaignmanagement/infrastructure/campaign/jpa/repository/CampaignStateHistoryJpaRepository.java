package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignStateHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignStateHistoryJpaRepository extends JpaRepository<CampaignStateHistory, Long> {

    List<CampaignStateHistory> findAllByCampaignId(Long campaignId);

}
