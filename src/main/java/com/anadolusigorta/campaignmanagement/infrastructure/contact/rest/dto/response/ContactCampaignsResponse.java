/* odeon_sukruo created on 26.04.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.product.rest.dto */

package com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.AvailableCampaign;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignReward;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.domain.operator.OperatorEnum;
import com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.request.CustomerCampaignsRequest;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactCampaignsResponse {
	private String contactNumber;
	private String transactionId;
	private LocalDateTime date;
	private List<ProductResponse> products;

	public static ContactCampaignsResponse fromModel(CustomerCampaignsRequest customerCampaignsRequest,
			Map<String, List<AvailableCampaign>> productsCampaigns) {
		log.info(String.format("Find Campaign Service Response Transactional ID: %s",
				customerCampaignsRequest.getTransactionId()));
		return ContactCampaignsResponse.builder()
				.products(productsCampaigns.keySet().stream().map(item -> ProductResponse.builder().policyType(item)
						.campaigns(productsCampaigns.get(item).stream()
								.map(ContactCampaignsResponse::toCampaignResponseModel).collect(Collectors.toList()))
						.build()).collect(Collectors.toList()))
				.contactNumber(customerCampaignsRequest.getContactNumber())
				.transactionId(customerCampaignsRequest.getTransactionId()).date(LocalDateTime.now()).build();
	}

	public static CampaignResponse toCampaignResponseModel(AvailableCampaign availableCampaign) {
		log.info(String.format("Find Campaign Service Response Campaign Information: %s", availableCampaign.toString()));

		return CampaignResponse.builder()
				.campaignName(availableCampaign.getCampaignInformation().getCampaignName())
				.campaignTitle(availableCampaign.getCampaignInformation().getCampaignTitle())
				.campaignType(availableCampaign.getCampaignInformation().getCampaignType().getName())
				.campaignId(availableCampaign.getCampaignInformation().getCampaignId())
				.ruleGroupId(availableCampaign.getRuleGroup().getRuleGroupId())
				.discount(availableCampaign.getRuleGroup().getCampaignReward() != null
						? toDiscountResponseModel(availableCampaign.getRuleGroup().getCampaignReward())
						: null)
				.reward(availableCampaign.getRuleGroup().getCampaignReward() != null
						? toRewardResponseModel(availableCampaign.getRuleGroup().getCampaignReward())
						: null)
				.isRelatedCampaign(availableCampaign.getRuleGroup().isRelatedCampaign())
				.isCrossSaleCampaign(availableCampaign.getRuleGroup().isCrossSale())
				.isSaleTaskCampaign(availableCampaign.getRuleGroup().isSaleTaskCampaign())
				.coverCodes(availableCampaign.getRuleGroup().getAttributeValues(Constants.COVER_CODE))
				.relatedTypes(availableCampaign.getRuleGroup().getAttributeValues(Constants.RELATED_TYPE))
				.isIsBankFaceToFaceBankingCampaign(availableCampaign.getRuleGroup().isIsBankCampaign())
				.campaignVersion(availableCampaign.getCampaignInformation().getVersion()).build();
	}

	private static RewardResponse toRewardResponseModel(CampaignReward campaignReward) {
		if (campaignReward.getCampaignRewardGift() != null) {
			var gift = campaignReward.getCampaignRewardGift();
			return RewardResponse.builder().rewardName(getRewardName(gift))
					.rewardType(gift.getRewardGiftType().getName()).rewardCount(gift.getTotalProductCount())
					.hasCampaignCode(campaignReward.getCampaignRewardGift() != null
							&& campaignReward.getCampaignRewardGift().getGiftCodeInformation() != null)
					.build();
		}
		return null;

	}

	private static String getRewardName(CampaignReward.CampaignRewardGift campaignRewardGift) {
		var giftType = campaignRewardGift.getRewardGiftType().getName();
		if (giftType.equals(Constants.GIFT_TYPE)) {
			return campaignRewardGift.getRewardGiftProduct().getName();
		} else if (giftType.equals(Constants.GIFT_TICKET_TYPE)) {
			return campaignRewardGift.getGiftCodeInformation().getRewardCompanyInformation().getName() + " "
					+ (campaignRewardGift.getValue()!=null?campaignRewardGift.getValue():
					campaignRewardGift.getGiftCodeInformation().getRewardGiftTicketType().getName());
		} else {
			return null;
		}

	}

	private static RewardDiscountResponse toDiscountResponseModel(CampaignReward campaignReward) {
		return campaignReward.getCampaignRewardDiscount() != null ? RewardDiscountResponse.builder()
						.discountType(campaignReward.getCampaignRewardDiscount()
								.getRewardDiscountKind() != null ?
								campaignReward.getCampaignRewardDiscount().getRewardDiscountKind().getName()
								:null)
						.discountValue(campaignReward.getCampaignRewardDiscount() != null
								? campaignReward.getCampaignRewardDiscount().getValue()
								: null)
						.hasCampaignCode(campaignReward.getCampaignRewardDiscount().getDiscountCodeInformation() != null)
				.coverCode(campaignReward.getCampaignRewardDiscount() != null &&
						campaignReward.getCampaignRewardDiscount().getCoverCodeType()!=null
						? CoverCodeRewardDiscountResponse.builder()
						.discountType(campaignReward.getCampaignRewardDiscount().getCoverCodeType().getName())
						.discountValue(campaignReward.getCampaignRewardDiscount().getCoverCodeDiscountValue())
						.build()
						: null)
						.build() : RewardDiscountResponse.builder().build();
	}

	@Setter
	@Getter
	@Builder
	public static class CampaignResponse {
		private Long campaignId;
		private String campaignName;
		private String campaignTitle;
		private String campaignType;
		private Long ruleGroupId;
		private RewardDiscountResponse discount;
		private RewardResponse reward;
		private Boolean isRelatedCampaign;
		private Boolean isCrossSaleCampaign;
		private Boolean isSaleTaskCampaign;
		private Boolean isIsBankFaceToFaceBankingCampaign;
		private Long campaignVersion;
		private List<String> coverCodes;
		private List<String> relatedTypes;

	}

	@Setter
	@Getter
	@Builder
	public static class RewardDiscountResponse {
		private String discountType;
		private String discountValue;
		private Boolean hasCampaignCode;
		private CoverCodeRewardDiscountResponse coverCode;

	}

	@Setter
	@Getter
	@Builder
	public static class CoverCodeRewardDiscountResponse {
		private String discountType;
		private String discountValue;

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

	@Setter
	@Getter
	@Builder
	public static class ProductResponse {
		private String policyType;
		private List<CampaignResponse> campaigns;
	}

}
