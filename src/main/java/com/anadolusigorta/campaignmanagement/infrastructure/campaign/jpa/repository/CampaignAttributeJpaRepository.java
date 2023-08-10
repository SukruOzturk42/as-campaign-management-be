package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignAttribute;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignAttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface CampaignAttributeJpaRepository extends JpaRepository<CampaignAttributeEntity, Long> {
	List<CampaignAttributeEntity> findByAttributeCampaignTypeIdAndCompanyCampaignStructureIdAndAttributeIsMatFalseAndAttributeIsRewardFalseAndAttributeIsActiveTrue(Long campaignType,
			Long structureId);


	CampaignAttributeEntity findByAttributeIdAndAttributeCampaignTypeIdAndCompanyCampaignStructureName(Long attributeId,Long campaignTypeId,String structureName);

	List<CampaignAttributeEntity> findByAttributeId(Long attributeId);

	Optional<CampaignAttributeEntity> findByAttributeName(String attributeName);

	List<CampaignAttributeEntity> findByParentId(Long parentId);

	List<CampaignAttributeEntity> findByAttributeCampaignTypeIdAndCompanyCampaignStructureName(Long campaignTypeId,String structureName);

	CampaignAttributeEntity findTopByOrderByIdDesc();

	List<CampaignAttributeEntity> findByAttributeIdAndCompanyCampaignStructureId(Long attributeId,Long structureId);

	List<CampaignAttributeEntity> findByAttributeIsMatTrue();

	List<CampaignAttributeEntity> findByAttributeIsRewardTrue();


}
