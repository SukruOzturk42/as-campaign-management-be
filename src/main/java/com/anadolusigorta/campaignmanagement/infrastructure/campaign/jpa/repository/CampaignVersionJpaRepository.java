package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignVersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CampaignVersionJpaRepository extends JpaRepository<CampaignVersionEntity, Long> {

	 Optional<CampaignVersionEntity> findByCampaignIdAndIsActiveVersionTrue(Long campaignId);

	Optional<CampaignVersionEntity> findByIdAndCampaignId(Long id,Long campaignId);

	Optional<CampaignVersionEntity> findFirstByCampaignIdOrderByCreatedAtDesc(Long campaignInformationId);

	List<CampaignVersionEntity> findVersionByCampaignId(@Param("campaignId") Long campaignId);
}
