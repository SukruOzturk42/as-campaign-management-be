package com.anadolusigorta.campaignmanagement.infrastructure.campaignstatus.jpa.repository;

import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignApprovalStatus;
import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignApprovalStatusEnum;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignstatus.jpa.entity.CampaignApprovalStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CampaignApprovalStatusJpaRepository extends JpaRepository<CampaignApprovalStatusEntity, Long> {

    Optional<CampaignApprovalStatusEntity> findById(Long campaignApprovalStatusId);
    Optional<CampaignApprovalStatusEntity> findByName(String name);
    Optional<CampaignApprovalStatusEntity> findByCampaignApprovalStatus(CampaignApprovalStatusEnum statusName);



}
