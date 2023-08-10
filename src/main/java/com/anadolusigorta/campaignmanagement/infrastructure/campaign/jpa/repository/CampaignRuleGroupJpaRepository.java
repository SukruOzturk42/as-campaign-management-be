package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignRuleGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignRuleGroupJpaRepository extends JpaRepository<CampaignRuleGroupEntity, Long> {


	List<CampaignRuleGroupEntity>  findByCampaignVersionId(Long versionId);

	List<CampaignRuleGroupEntity>  findByContactGroupId(Long contactGroupId);

	List<CampaignRuleGroupEntity> findByCampaignId(Long campaignId);

	List<CampaignRuleGroupEntity> findByTaskGroupId(Long taskGroupId);


}
