/* odeon_sukruo created on 4.05.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity */

package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignReward;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.entity.ReferenceTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.DiscountDetailTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.DiscountKindEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.RewardRoleEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.DiscountTypeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rule_group_reward_discount")
public class RuleGroupRewardDiscountEntity extends AbstractEntity {

	@OneToOne
	@JoinColumn(name = "campaign_rule_group_reward_id")
	private RuleGroupRewardEntity ruleGroupRewardEntity;

	@ManyToOne
	@JoinColumn(name = "discount_detail_type_id")
	private DiscountDetailTypeEntity discountDetailType;

	@ManyToOne
	@JoinColumn(name = "discount_type_id")
	private DiscountTypeEntity discountType;

	@ManyToOne
	@JoinColumn(name = "discount_kind_id")
	private DiscountKindEntity discountKind;



	@ManyToOne
	@JoinColumn(name = "discount_code_information_id")
	private DiscountCodeInformationEntity discountCodeInformation ;

	@Column(name = "value")
	private String value;


	@Column(name = "cover_code_discount_value")
	private String coverCodeDiscountValue;

	@ManyToOne
	@JoinColumn(name = "cover_code_type_id")
	private ReferenceTypeEntity coverCodeType;


	@Column(name = "total_product_count")
	private Long totalProductCount;

	@Column(name = "total_customer_product_count")
	private Long totalCustomerProductCount;


	public CampaignReward.CampaignRewardDiscount toModel(){
		return CampaignReward.CampaignRewardDiscount.builder()
				.rewardDiscountId(getId())
				.value(value)
				.rewardDiscountDetailType(discountDetailType!=null?discountDetailType.toModel():null)
				.rewardDiscountKind(discountKind!=null?discountKind.toModel():null)
				.rewardDiscountType(discountType!=null?discountType.toModel():null)
				.totalCustomerProductCount(totalCustomerProductCount)
				.totalProductCount(totalProductCount)
				.discountCodeInformation(discountCodeInformation!=null?discountCodeInformation.toModel():null)
				.coverCodeDiscountValue(coverCodeDiscountValue)
				.coverCodeType(coverCodeType!=null?coverCodeType.toModel():null)
				.build();
	}

}
