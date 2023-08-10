/* odeon_sukruo created on 4.05.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity */

package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.attribute.model.AttributeTypeEnum;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRuleAttribute;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.domain.operator.OperatorEnum;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "campaign_attribute_rule")
public class CampaignAttributeRuleEntity extends AbstractEntity {



	@ManyToOne
	@JoinColumn(name = "campaign_attribute_id")
	private CampaignAttributeEntity campaignAttribute;

	@Enumerated(EnumType.STRING)
	private OperatorEnum operator;

	 private String value;

	@Enumerated(EnumType.STRING)
	private AttributeTypeEnum attributeType;

	@ManyToOne
	@JoinColumn(name = "campaign_attribute_rule_group_id")
	private CampaignAttributeRuleGroupEntity campaignAttributeRuleGroup;



	public CampaignRuleAttribute toModel(){
		return CampaignRuleAttribute.builder()
				.ruleAttributeId(getId())
				.parameterId(campaignAttribute!=null&&campaignAttribute.getAttribute()!=null?
						campaignAttribute.getId()
						:null)
				.attributeType(attributeType)
				.parameter(campaignAttribute!=null&&campaignAttribute.getAttribute()!=null?
						campaignAttribute.getAttribute().getName()
						:null)
				.operator(operator)
				.dataType(campaignAttribute!=null&&campaignAttribute.getAttribute()!=null?
						campaignAttribute.getAttribute().getDataType()
						:null)
				.value(value!=null?
						new ArrayList<>(Arrays.asList(value.split(Constants.ATTRIBUTE_VALUE_DELIMITER))):
						null)
				.build();
	}


}
