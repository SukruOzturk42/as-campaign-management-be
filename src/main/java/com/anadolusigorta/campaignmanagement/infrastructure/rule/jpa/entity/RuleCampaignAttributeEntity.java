/* odeon_sukruo created on 17.05.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity */

package com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRuleAttribute;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.domain.operator.OperatorEnum;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignAttributeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.entity.ReferenceTypeEntity;
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
@Table(name = "rule_campaign_attribute")
public class RuleCampaignAttributeEntity extends AbstractEntity {


	@ManyToOne
	@JoinColumn(name = "rule_id")
	private RuleEntity rule;

	@ManyToOne
	@JoinColumn(name = "campaign_attribute_id")
	private CampaignAttributeEntity campaignAttribute;

	@Enumerated(EnumType.STRING)
	private OperatorEnum operator;

	@ManyToOne
	@JoinColumn(name = "reference_id")
	private ReferenceTypeEntity referenceType;

	private String value;




	public CampaignRuleAttribute toModel(){
		return CampaignRuleAttribute.builder()
				.operator(operator)
				.parameter(campaignAttribute.getAttribute().getName())
				.value(value!=null
						?new ArrayList<>(Arrays.asList(value.split(Constants.ATTRIBUTE_VALUE_DELIMITER))):
						null)
				.order(campaignAttribute.getAttributeOrder())
				.dataType(campaignAttribute.getAttribute()!=null?
						campaignAttribute.getAttribute().getDataType()
						:null)
				.build();
	}
}
