/* odeon_sukruo created on 26.04.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.product.rest.dto */

package com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.AvailableCampaign;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignReward;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactCampaignResponse {
	private Long campaignId;
	private String campaignName;
	private String campaignType;
	private Long ruleGroupId;
	private RewardDiscountResponse discount;
	private RewardResponse reward;
	private Boolean isRelatedCampaign;
	private Boolean isCrossSaleCampaign;
	private Long campaignVersion;



	public static ContactCampaignResponse fromModel(AvailableCampaign availableCampaign) {
		return ContactCampaignResponse.builder()
				.campaignName(availableCampaign.getCampaignInformation().getCampaignName())
				.campaignType(availableCampaign.getCampaignInformation().getCampaignType().getName())
				.campaignId(availableCampaign.getCampaignInformation().getCampaignId())
				.ruleGroupId(availableCampaign.getRuleGroup().getRuleGroupId())
				.discount(availableCampaign.getRuleGroup().getCampaignReward() != null
						? toDiscountResponseModel(availableCampaign.getRuleGroup().getCampaignReward())
						: null)
				.reward(availableCampaign.getRuleGroup().getCampaignReward() != null
						? toRewardResponseModel(availableCampaign.getRuleGroup().getCampaignReward())
						: null)
				.isRelatedCampaign(availableCampaign.getRuleGroup() != null
						&& availableCampaign.getRuleGroup().getRelatedCooperation() != null)
				.isCrossSaleCampaign(availableCampaign.getRuleGroup() != null
						&& availableCampaign.getRuleGroup().getOwnerProduct() != null)
				.campaignVersion(availableCampaign.getCampaignInformation().getVersion()).build();
	}

	private static RewardResponse toRewardResponseModel(CampaignReward campaignReward) {
		if (campaignReward.getCampaignRewardGift() != null) {
			var gift = campaignReward.getCampaignRewardGift();
			return RewardResponse.builder()
					.rewardName(getRewardName(gift))
					.rewardType(gift.getRewardGiftType().getName())
					.rewardCount(gift.getTotalProductCount())
					.hasCampaignCode(campaignReward.getCampaignRewardGift() != null
							&& campaignReward.getCampaignRewardGift().getGiftCodeInformation() != null)
					.build();
		}
		return null;

	}

	private static String getRewardName(CampaignReward.CampaignRewardGift campaignRewardGift) {
		var giftType=campaignRewardGift.getRewardGiftType().getName();
		if(giftType.equals(Constants.GIFT_TYPE)){
			return campaignRewardGift
					.getRewardGiftProduct()
					.getName();
		}else if(giftType.equals(Constants.GIFT_TICKET_TYPE)){
			return campaignRewardGift.getGiftCodeInformation().getRewardCompanyInformation().getName() + " "
					+ campaignRewardGift.getGiftCodeInformation().getRewardGiftTicketType().getName();
		}else{
			return null;
		}

	}

	private static RewardDiscountResponse toDiscountResponseModel(CampaignReward campaignReward) {
		return RewardDiscountResponse.builder()
				.discountType(campaignReward.getCampaignRewardDiscount() != null
						&& campaignReward.getCampaignRewardDiscount().getRewardDiscountKind() != null
								? campaignReward.getCampaignRewardDiscount().getRewardDiscountKind().getName()
								: null)
				.discountValue(campaignReward.getCampaignRewardDiscount() != null
						? campaignReward.getCampaignRewardDiscount().getValue()
						: null)
				.hasCampaignCode(campaignReward.getCampaignRewardDiscount() != null
						&& campaignReward.getCampaignRewardDiscount().getDiscountCodeInformation() != null)
				.build();
	}



	@Setter
	@Getter
	@Builder
	public static class RewardDiscountResponse {
		private String discountType;
		private String discountValue;
		private Boolean hasCampaignCode;

	}

	@Setter
	@Getter
	@Builder
	public static class RewardResponse {
		private String rewardType;
		private Long rewardCount;
		private String rewardName;
		private Boolean hasCampaignCode;

	}


}
