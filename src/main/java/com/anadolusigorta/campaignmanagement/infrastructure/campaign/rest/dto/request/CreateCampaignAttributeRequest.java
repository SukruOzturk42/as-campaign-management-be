/* odeon_sukruo created on 3.05.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request */

package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CreateCampaign.*;
import com.anadolusigorta.campaignmanagement.domain.operator.AttributeOperator;
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
public class CreateCampaignAttributeRequest {

	private Long structureId;
	private Long attributeId;
	private AttributeOperator attributeOperator;
	private String value;
	private List<CreateCampaignAttributeRequest> children;

	public CampaignAttribute toModel(){
		return CampaignAttribute.builder()
				.structureId(structureId)
				.attributeId(attributeId)
				.operator(attributeOperator.getOperatorEnum())
				.build();
	}
	public List<CampaignAttribute> toListOfModel(List<CreateCampaignAttributeRequest> children){
		return children.stream().map(CreateCampaignAttributeRequest::toModel).collect(Collectors.toList());
	}
}
