/* odeon_sukruo created on 23.05.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.campaign.model */

package com.anadolusigorta.campaignmanagement.domain.campaign.model;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.DiscountCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.GiftCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.referencetype.model.AttributeReferenceType;
import com.anadolusigorta.campaignmanagement.domain.reward.model.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CampaignReward implements Serializable {

	private Long rewardId;

	private Long campaignRuleGroupId;

	private Long campaignId;

	private CampaignRewardDiscount campaignRewardDiscount;

	private CampaignRewardGift campaignRewardGift;


	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class CampaignRewardDiscount implements Serializable {

		private Long rewardDiscountId;

		private Long campaignId;

		private String value;

		private String name;

		private RewardDiscountDetailType rewardDiscountDetailType;

		private RewardDiscountType rewardDiscountType;

		private RewardDiscountKind rewardDiscountKind;

		private DiscountCodeInformation discountCodeInformation;

		private String coverCodeDiscountValue;

		private AttributeReferenceType coverCodeType;


		private Long totalProductCount;

		private Long totalCustomerProductCount;


	}

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class CampaignRewardGift implements Serializable {

		private Long rewardGiftId;

		private String value;

		private Long totalProductCount;

		private Long customerProductCount;

		private Long customerCount;

		private RewardGiftType rewardGiftType;

		private GiftCodeInformation giftCodeInformation;

		private RewardGiftProduct rewardGiftProduct;

		private RewardGiftKind rewardGiftKind;

		private RewardGiftDeliveryType rewardGiftDeliveryType;

		private RewardGiftPaymentType rewardGiftPaymentType;

		private RewardGiftDeliveryStartType rewardGiftDeliveryStartType;

		private Long productDeliveryOrder;

		private LocalDateTime lastSendTime;

		private Integer dayAfterDeliveryStart;

		private RewardGiftSendMethodType rewardGiftSendMethodType;

		private Long rewardProductId;

		private String rewardGiftTemplateId;

		private RewardRole rewardRole;

		private List<CampaignRuleAttribute> sendRules;

	}
}
