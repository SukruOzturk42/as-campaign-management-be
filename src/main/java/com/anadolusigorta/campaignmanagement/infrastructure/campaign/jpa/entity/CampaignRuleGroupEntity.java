/* odeon_sukruo created on 12.05.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity */

package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity;


import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRuleAttribute;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRuleGroup;
import com.anadolusigorta.campaignmanagement.domain.contact.model.ContactGroup;
import com.anadolusigorta.campaignmanagement.domain.operator.ConjunctionOperatorEnum;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.contact.jpa.entity.ContactGroupEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.KnimeTaskTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.TaskGroupEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "campaign_rule_group")
public class CampaignRuleGroupEntity extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name = "campaign_id", nullable = false)
	private CampaignEntity campaign;

	@ManyToOne
	@JoinColumn(name = "campaign_version_id")
	private CampaignVersionEntity campaignVersion;

	@Column(name = "name", nullable = false)
	private String name;

	@Enumerated(EnumType.STRING)
	private ConjunctionOperatorEnum conjunctionOperator;

	@OneToMany(mappedBy = "campaignRuleGroup",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<CampaignRuleEntity> campaignRules;

	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "reward_id")
	private RuleGroupRewardEntity reward;


	@ManyToOne
	private CampaignRuleGroupEntity parent;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "owner_product_id")
	private CampaignRuleGroupOwnerProductEntity ownerProduct;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "related_cooperation_id")
	private CampaignRuleGroupRelatedCooperationEntity relatedCooperation;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "contact_product_id")
	private CampaignRuleGroupContactProductEntity contactProduct;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "mat_group_id")
	private CampaignRuleGroupMatEntity matGroup;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "is_bank_id")
	private CampaignRuleGroupIsBankEntity isBank;

	@OneToMany(mappedBy = "parent")
	private Set<CampaignRuleGroupEntity> children;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "contact_group_id")
	private ContactGroupEntity  contactGroup;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "task_group_id")
	private TaskGroupEntity taskGroup;

	public CampaignRuleGroup toModel(){
		return CampaignRuleGroup.builder()
				.campaignId(campaign.getId())
				.campaignVersionId(campaignVersion.getId())
				.campaignVersion(campaignVersion.getVersion())
				.ruleGroupId(super.getId())
				.name(name)
				.rules(campaignRules!=null?campaignRules.stream()
						.map(CampaignRuleEntity::toModel)
						.sorted(Comparator.comparingLong(CampaignRuleAttribute::getRuleAttributeId))
						.collect(Collectors.toList()):null)
				.conjunctionOperator(conjunctionOperator)
				.campaignReward(reward!=null?reward.toModel():null)
				.ownerProduct(ownerProduct!=null?ownerProduct.toModel():null)
				.relatedCooperation(relatedCooperation!=null?relatedCooperation.toModel():null)
				.isBank(isBank!=null?isBank.toModel():null)
				.contactProduct(contactProduct!=null?contactProduct.toModel():null)
				.matGroup(matGroup!=null?matGroup.toModel():null)
				.contactGroup(contactGroup!=null?
						ContactGroup.builder().contactGroupId(contactGroup.getId())
								.groupName(contactGroup.getGroupName()).build()
						:null)
				.taskGroup(taskGroup!=null?taskGroup.toModel():null)
				.build();
	}


}
