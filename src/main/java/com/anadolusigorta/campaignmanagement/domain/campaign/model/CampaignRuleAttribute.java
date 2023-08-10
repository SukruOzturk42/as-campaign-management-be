/* odeon_sukruo created on 3.05.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.campaign.model */

package com.anadolusigorta.campaignmanagement.domain.campaign.model;

import com.anadolusigorta.campaignmanagement.domain.attribute.model.AttributeTypeEnum;
import com.anadolusigorta.campaignmanagement.domain.operator.OperatorEnum;
import com.anadolusigorta.campaignmanagement.domain.referencetype.model.DataType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CampaignRuleAttribute implements Serializable {
	private Long ruleAttributeId;
    private Long  parameterId;
	private String parameter;
	private OperatorEnum operator;
	private List<String> value=new ArrayList<>();
	private AttributeTypeEnum attributeType;
	private CampaignRuleGroup rule;
	private CampaignRuleGroup subProduct;
	private DataType dataType;
	private Long order;


}
