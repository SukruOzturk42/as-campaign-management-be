/* dks20165 created on 16.06.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response */

package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.attribute.model.AttributeTypeEnum;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRuleAttribute;
import com.anadolusigorta.campaignmanagement.domain.operator.OperatorEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public  class CampaignAttributeResponse {

	private Long id;
	private Long ruleAttributeId;
	private Long attributeId;
	private Long structureId;
	private OperatorEnum operator;
	private AttributeTypeEnum type;
	private List<String> value;
	private CampaignRuleGroupResponse subProduct;

	public static CampaignAttributeResponse fromModel(CampaignRuleAttribute campaignAttribute){
		return CampaignAttributeResponse.builder()
				.id(campaignAttribute.getRuleAttributeId())
				.ruleAttributeId(campaignAttribute.getRuleAttributeId())
				.attributeId(campaignAttribute.getParameterId())
				.value(campaignAttribute.getValue())
				.type(campaignAttribute.getAttributeType())
				.operator(campaignAttribute.getOperator())
				.subProduct(campaignAttribute.getSubProduct()!=null?
						CampaignRuleGroupResponse.builder()
								.ruleGroupId(campaignAttribute.getSubProduct().getRuleGroupId())
								.attributes(fromListOfModel(campaignAttribute.getSubProduct().getRules()))
								.conjunctionOperator(campaignAttribute.getSubProduct().getConjunctionOperator())
								.name(campaignAttribute.getSubProduct().getName())
								.build()
						:null)
				.build();
	}

	public static List<CampaignAttributeResponse> fromListOfModel(List<CampaignRuleAttribute> campaignRuleAttributes){

		return campaignRuleAttributes.stream()
				.map(CampaignAttributeResponse::fromModel)
				.collect(Collectors.toList());
	}
}