/* odeon_sukruo created on 12.05.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity */

package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity;


import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignAttributeRuleGroup;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRuleGroup;
import com.anadolusigorta.campaignmanagement.domain.operator.ConjunctionOperatorEnum;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "campaign_attribute_rule_group")
public class CampaignAttributeRuleGroupEntity extends AbstractEntity {


	@Column(name = "name")
	private String name;

	@Enumerated(EnumType.STRING)
	private ConjunctionOperatorEnum conjunctionOperator;

	@OneToMany(mappedBy = "campaignAttributeRuleGroup",cascade = CascadeType.ALL)
	private Set<CampaignAttributeRuleEntity> campaignRules;


	public CampaignRuleGroup toModel(){
		return CampaignRuleGroup.builder()
				.ruleGroupId(getId())
				.name(name)
				.rules(campaignRules!=null?campaignRules.stream()
						.map(CampaignAttributeRuleEntity::toModel)
						.collect(Collectors.toList()):null)
				.conjunctionOperator(conjunctionOperator)
				.build();
	}


}
