package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignApprovalNotificationDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CampaignApprovalNotificationDetailJpaRepository extends JpaRepository<CampaignApprovalNotificationDetailEntity,Long> {

    Optional<CampaignApprovalNotificationDetailEntity> findByCampaignIdAndVersion(Long campaignId,Long version);

}
