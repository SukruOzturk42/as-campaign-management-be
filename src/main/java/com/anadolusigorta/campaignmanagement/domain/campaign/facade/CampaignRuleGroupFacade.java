package com.anadolusigorta.campaignmanagement.domain.campaign.facade;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRuleGroup;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.CampaignRuleGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignRuleGroupFacade {

	private final CampaignRuleGroupRepository campaignRuleGroupRepository;

	public List<CampaignRuleGroup> getCampaignRuleGroupsByCampaignId(Long campaignId) {
		return campaignRuleGroupRepository.finCampaignRuleGroupsByCampaignId(campaignId);
	}

	public List<CampaignRuleGroup> getRuleGroupsWithAvailableCodeSetCampaignRuleGroupsByCampaignId(Long campaignId) {
		return campaignRuleGroupRepository.findRuleGroupsWithAvailableCodeSetCampaignRuleGroupsByCampaignId(campaignId);
	}

	public List<String> getCampaignRuleGroupNamesByCampaignId(Long campaignId) {
		return campaignRuleGroupRepository.getCampaignRuleGroupNamesByCampaignId(campaignId);
	}

}
