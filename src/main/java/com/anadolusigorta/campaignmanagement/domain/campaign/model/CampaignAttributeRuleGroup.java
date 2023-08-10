/* odeon_sukruo created on 3.05.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.campaign.model */

package com.anadolusigorta.campaignmanagement.domain.campaign.model;

import com.anadolusigorta.campaignmanagement.domain.operator.ConjunctionOperatorEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignAttributeRuleGroup {
	private Long ruleGroupId;
	private String name;
	private ConjunctionOperatorEnum conjunctionOperator;
	private List<CampaignRuleAttribute> rules;
}
