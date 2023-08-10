/* odeon_sukruo created on 4.05.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity */

package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.attribute.model.AttributeTypeEnum;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRule;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRuleAttribute;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.domain.operator.OperatorEnum;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.entity.RuleEntity;
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
@Table(name = "campaign_rule")
public class CampaignRuleEntity extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name = "campaign_rule_group_id")
	private CampaignRuleGroupEntity campaignRuleGroup;


	@ManyToOne
	@JoinColumn(name = "campaign_rule_group_owner_product_id")
	private CampaignRuleGroupOwnerProductEntity ownerProduct;

	@ManyToOne
	@JoinColumn(name = "campaign_rule_group_related_cooperation_id")
	private CampaignRuleGroupRelatedCooperationEntity relatedCooperation;

	@ManyToOne
	@JoinColumn(name = "campaign_rule_group_contact_product_id")
	private CampaignRuleGroupContactProductEntity contactProduct;

	@ManyToOne
	@JoinColumn(name = "campaign_rule_group_mat_group_id")
	private CampaignRuleGroupMatEntity matGroup;

	@ManyToOne
	@JoinColumn(name = "campaign_rule_group_is_bank")
	private CampaignRuleGroupIsBankEntity isBank;

	@ManyToOne
	@JoinColumn(name = "campaign_attribute_id")
	private CampaignAttributeEntity campaignAttribute;

	@Enumerated(EnumType.STRING)
	private OperatorEnum operator;

	@Column(length = 2000)
    private String value;

	@Enumerated(EnumType.STRING)
	private AttributeTypeEnum attributeType;

	@ManyToOne
	@JoinColumn(name = "rule_id")
	private RuleEntity rule;

	@ManyToOne
	@JoinColumn(name = "sub_product_id")
	private CampaignAttributeRuleGroupEntity subProduct;

	@ManyToOne
	@JoinColumn(name = "campaign_rule_group_reward_gift_id")
	private RuleGroupRewardGiftEntity ruleGroupRewardGift;


	public CampaignRuleAttribute toModel(){
		return CampaignRuleAttribute.builder()
				.ruleAttributeId(getId())
				.parameterId(campaignAttribute!=null&&campaignAttribute.getAttribute()!=null?
						campaignAttribute.getId()
						:rule.getId())
				.attributeType(attributeType)
				.rule(attributeType.equals(AttributeTypeEnum.RULE)?rule.toModel():null)
				.parameter(campaignAttribute!=null&&campaignAttribute.getAttribute()!=null?
						campaignAttribute.getAttribute().getName()
						:null)
				.dataType(campaignAttribute!=null&&campaignAttribute.getAttribute()!=null?
						campaignAttribute.getAttribute().getDataType()
						:null)
				.operator(operator)
				.order(campaignAttribute!=null?
						campaignAttribute.getAttributeOrder()
						: 0)
				.value(value!=null?
						new ArrayList<>(Arrays.asList(value.split(Constants.ATTRIBUTE_VALUE_DELIMITER))):
						null)
				.subProduct(subProduct!=null?subProduct.toModel():null)
				.build();
	}

	public CampaignRule toCampaignRuleModel(){
		return CampaignRule.builder()
				.campaignId(campaignRuleGroup.getCampaign().getId())
				.campaignName(campaignRuleGroup.getCampaignVersion().getCampaignInformation().getCampaignName())
				.campaignVersion(campaignRuleGroup.getCampaignVersion().getVersion())
				.build();
	}
}
