/* odeon_sukruo created on 21.05.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.handler */

package com.anadolusigorta.campaignmanagement.domain.handler;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.AvailableCampaign;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.Campaign;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.contact.model.ContactCampaignInformation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
@AllArgsConstructor
public class CampaignHandler {

	private final CampaignRuleGroupHandler campaignRuleGroupHandler;


	public AvailableCampaign getCampaignForProduct(ContactCampaignInformation contactCampaignInformation, Campaign campaign) {

		var ruleGroup = campaignRuleGroupHandler.findFirstMatchedCampaignRuleGroupForProduct(contactCampaignInformation,
				campaign);
		if (ruleGroup != null) {
			return AvailableCampaign.builder()
					.campaignInformation(campaign.getCampaignInformation())
					.ruleGroup(ruleGroup).build();

		} else {
			return null;
		}

	}

	public AvailableCampaign executeSearchOperation(Map<String,Object>  parameters, Campaign campaign) {

		var ruleGroup = campaignRuleGroupHandler.findFirstContainsRuleGroupForProduct(parameters, campaign);
		if (ruleGroup != null) {
			return AvailableCampaign.builder()
					.campaignInformation(campaign.getCampaignInformation())
					.ruleGroup(ruleGroup).build();

		} else {
			return null;
		}

	}


	public AvailableCampaign getAvailableCampaignToParticipate(ContactCampaignInformation contactCampaignInformation,
															   Campaign campaign) {

		var ruleGroup = campaignRuleGroupHandler.findFirstMatchedCampaignRuleGroupForProduct(contactCampaignInformation, campaign);
		if (ruleGroup != null) {
			return AvailableCampaign.builder()
					.campaignInformation(campaign.getCampaignInformation())
					.ruleGroup(ruleGroup).build();

		} else {
			throw new CampaignManagementException("no.right.to.participate");
		}

	}
}
