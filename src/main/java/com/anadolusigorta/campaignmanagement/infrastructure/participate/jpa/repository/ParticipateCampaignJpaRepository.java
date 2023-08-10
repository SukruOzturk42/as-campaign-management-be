package com.anadolusigorta.campaignmanagement.infrastructure.participate.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.participate.jpa.entity.ParticipateCampaignEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface ParticipateCampaignJpaRepository extends JpaRepository<ParticipateCampaignEntity, Long> {

	Optional<ParticipateCampaignEntity> findTopByCampaignInformationCampaignIdAndCustomerNumberOrderByNumberOfParticipationDesc(Long campaignId,String customerNumber);
	Optional<ParticipateCampaignEntity> findTopByCampaignInformationCampaignIdOrderByNumberOfParticipationDesc(Long campaignId);

}
