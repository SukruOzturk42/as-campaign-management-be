package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository;

import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignStatusEnum;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CustomerCampaignEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.repository.SaleCampaignJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

public interface CustomerCampaignJpaRepository extends JpaRepository<CustomerCampaignEntity, Long> , JpaSpecificationExecutor<CustomerCampaignEntity> {

	List<CustomerCampaignEntity>  findByCampaignVersionCampaignInformationCampaignStatusCampaignStatus(
			CampaignStatusEnum campaignStatusEnum);

	Optional<CustomerCampaignEntity> findByCampaign(CampaignEntity campaignEntity);

	Optional<CustomerCampaignEntity> findByCampaignAndCampaignVersionIsActiveVersionTrue(CampaignEntity campaignEntity);

	Optional<CustomerCampaignEntity> findByCampaignId(Long campaignId);

	Page<CustomerCampaignEntity> findByCampaignIdAndCampaignVersionCampaignInformationCampaignName(Long campaignId,String campaignName,Pageable pageable);

	List<CustomerCampaignEntity> findByCampaignVersionCampaignInformationIsStartedRewardSend(Boolean reward);



	Page<CustomerCampaignEntity> findByCampaignVersionCampaignInformationCampaignNameContains(String campaignName,Pageable pageable);


	Optional<CustomerCampaignEntity> findByCampaignIdAndCampaignVersionCampaignInformationCampaignStartDateLessThanAndCampaignVersionCampaignInformationCampaignEndDateGreaterThan(Long campaignId,
			LocalDateTime startDate,LocalDateTime endDate);

	Optional<CustomerCampaignEntity> findByCampaignIdAndCampaignVersionVersion(Long campaignId,Long campaignVersion);

	List<CustomerCampaignEntity> findByCampaignVersionCampaignInformationCampaignTypeName(String type);

	Page<CustomerCampaignEntity> findByCampaignVersionCampaignInformationCampaignTypeName(String type,Pageable pageable);
}
