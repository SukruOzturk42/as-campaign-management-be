/* odeon_sukruo created on 26.04.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.product.rest.dto */

package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request;


import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignCriteria;
import com.anadolusigorta.campaignmanagement.domain.operator.OperatorEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignCriteriaRequest {


	private Long campaignId;

	private String campaignName;

	private String campaignStatusType;

	private Long campaignTypeId;

	private Long attributeId;

	private List<String> value;

	private OperatorEnum operator;

	public CampaignCriteria toModel(){
		return CampaignCriteria.builder()
				.campaignName(campaignName)
				.campaignId(campaignId)
				.campaignTypeId(campaignTypeId)
				.campaignStatusType(campaignStatusType)
				.operator(operator)
				.attributeId(attributeId)
				.value(value)
				.build();
	}


}
