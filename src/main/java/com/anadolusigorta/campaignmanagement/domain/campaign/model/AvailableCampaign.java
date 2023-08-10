/* odeon_sukruo created on 3.05.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.campaign.model */

package com.anadolusigorta.campaignmanagement.domain.campaign.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvailableCampaign {
	private CampaignInformation campaignInformation;
	private CampaignRuleGroup ruleGroup=CampaignRuleGroup.builder().build();

}
