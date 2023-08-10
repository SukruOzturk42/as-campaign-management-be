package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignInformationEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignstatus.jpa.entity.CampaignStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.List;

public interface CampaignInformationJpaRepository extends JpaRepository<CampaignInformationEntity, Long>, JpaSpecificationExecutor<CampaignInformationEntity> {

    List<CampaignInformationEntity> findByCampaignId(Long campaignId);

    List<CampaignInformationEntity> findByCampaignTypeIdIn(List<Long> ids);

    CampaignInformationEntity findByCampaignVersionId(Long id);

    List<CampaignInformationEntity> findByIsStartedRewardSendTrueAndIsTriggeredRewardSendTrue();

    List<CampaignInformationEntity> findByCampaignVersionIsActiveVersionTrueAndCampaignStatusId(Long campaignStatusId);

    List<CampaignInformationEntity> findByCampaignVersionIsActiveVersionTrueAndCampaignStatusIdAndCampaignStartDateAfter(Long campaignStatusId, LocalDateTime currentTime);

    List<CampaignInformationEntity> findByCampaignVersionIsActiveVersionTrueAndCampaignStatusIdAndCampaignEndDateBefore(Long campaignStatusId, LocalDateTime currentTime);

    List<CampaignInformationEntity> findByCampaignVersionIsActiveVersionTrueAndCampaignApprovalStatusId(Long campaignApprovalStatusId);

    List<CampaignInformationEntity> findByCampaignVersionIsActiveVersionTrueAndCampaignStatusIdAndCampaignApprovalStatusId(Long campaignStatusId, Long campaignApprovalStatusId);

    List<CampaignInformationEntity> findByCampaignStatusAndVersionStartDateBefore(CampaignStatusEntity campaignStatus, LocalDateTime date);
}
