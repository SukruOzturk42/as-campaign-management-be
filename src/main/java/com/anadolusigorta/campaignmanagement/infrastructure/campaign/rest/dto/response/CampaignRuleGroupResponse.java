/* dks20165 created on 18.06.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response */

package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRuleGroup;
import com.anadolusigorta.campaignmanagement.domain.operator.ConjunctionOperatorEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignRuleGroupResponse {
	private Long id;
	private Long ruleGroupId;
	private String name;
	private Long contactGroupId;
	private Long taskGroupId;
	private ConjunctionOperatorEnum conjunctionOperator;
	private List<CampaignAttributeResponse> attributes;
	private RuleGroupRewardResponse ruleGroupReward;
	private CampaignRuleGroupResponse ownerProduct;
	private CampaignRuleGroupResponse matRuleGroup;
	private CampaignRuleGroupResponse relatedCooperation;
	private CampaignRuleGroupResponse isBankRuleGroup;

	public static CampaignRuleGroupResponse fromModel(CampaignRuleGroup campaignRuleGroup){

		return CampaignRuleGroupResponse.builder()
				.id(campaignRuleGroup.getRuleGroupId())
				.ruleGroupId(campaignRuleGroup.getRuleGroupId())
				.name(campaignRuleGroup.getName())
				.contactGroupId(campaignRuleGroup.getContactGroup()!=null?
						campaignRuleGroup.getContactGroup().getContactGroupId()
						:null)
				.taskGroupId(campaignRuleGroup.getTaskGroup()!=null?
						campaignRuleGroup.getTaskGroup().getId()
						:null)
				.attributes(CampaignAttributeResponse.fromListOfModel(campaignRuleGroup.getRules()))
				.ruleGroupReward(campaignRuleGroup.getCampaignReward()!=null?
						RuleGroupRewardResponse.fromModel(campaignRuleGroup.getCampaignReward())
						:RuleGroupRewardResponse.builder().build())
				.conjunctionOperator(campaignRuleGroup.getConjunctionOperator())
				.ownerProduct(campaignRuleGroup.getOwnerProduct()!=null?
						fromModel(campaignRuleGroup.getOwnerProduct())
						:null)
				.matRuleGroup(campaignRuleGroup.getMatGroup()!=null?
						fromModel(campaignRuleGroup.getMatGroup())
						:null)
				.relatedCooperation(campaignRuleGroup.getRelatedCooperation()!=null?
						fromModel(campaignRuleGroup.getRelatedCooperation())
						:null)
				.isBankRuleGroup(campaignRuleGroup.getIsBank()!=null?
						fromModel(campaignRuleGroup.getIsBank())
						:null)
				.build();
	}

	public static List<CampaignRuleGroupResponse> fromListOfModel(List<CampaignRuleGroup> campaignRuleGroups){
		return campaignRuleGroups.stream()
				.map(CampaignRuleGroupResponse::fromModel)
				.collect(Collectors.toList());
	}
}
