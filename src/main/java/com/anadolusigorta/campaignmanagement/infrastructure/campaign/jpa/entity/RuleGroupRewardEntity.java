/* odeon_sukruo created on 4.05.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity */

package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignReward;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.UsedCodeGroupInformation;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rule_group_reward")
public class RuleGroupRewardEntity extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name = "campaign_id", nullable = false)
	private CampaignEntity campaign;

	@ManyToOne
	@JoinColumn(name = "campaign_rule_group_id", nullable = false)
	private CampaignRuleGroupEntity ruleGroup;


	@OneToOne
	@JoinColumn(name = "campaign_rule_group_reward_discount_id")
	private RuleGroupRewardDiscountEntity discount;

	@OneToOne
	@JoinColumn(name = "campaign_rule_group_reward_gift_id")
	private RuleGroupRewardGiftEntity gift;


	public CampaignReward toModel(){
		return CampaignReward.builder()
				.rewardId(getId())
				.campaignRuleGroupId(ruleGroup.getId())
				.campaignId(campaign.getId())
				.campaignRewardDiscount(discount!=null?discount.toModel():null)
				.campaignRewardGift(gift!=null?gift.toModel():null)
				.build();
	}

	public UsedCodeGroupInformation toDiscountInformationModel() {
		return UsedCodeGroupInformation.builder()
				.campaignId(ruleGroup.getCampaign().getId())
				.campaignVersion(ruleGroup.getCampaignVersion().getVersion())
				.campaignName(ruleGroup.getCampaignVersion().getCampaignInformation().getCampaignName())
				.build();
	}

}
