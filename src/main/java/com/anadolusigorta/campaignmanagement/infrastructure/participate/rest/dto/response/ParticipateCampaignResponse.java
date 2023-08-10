/* odeon_sukruo created on 26.04.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.product.rest.dto */

package com.anadolusigorta.campaignmanagement.infrastructure.participate.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.participate.model.ParticipatedCustomerCampaign;
import com.anadolusigorta.campaignmanagement.infrastructure.participate.rest.dto.request.ParticipateCampaignRequest;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipateCampaignResponse {
	private String customerNumber;
	private String transactionId;
	private LocalDateTime updateDate;
	private CampaignResponse campaign;


	@Setter
	@Getter
	@Builder
	public static class CampaignResponse {
		private Long campaignId;
		private String campaignName;
		private String campaignType;
		private RewardDiscountResponse discount;
		private RewardResponse reward;


	}

	@Setter
	@Getter
	@Builder
	public static class RewardDiscountResponse {
		private String discountType;
		private String discountValue;
		private String code;
		private LocalDateTime codeExpireDate;

	}

	@Setter
	@Getter
	@Builder
	public static class RewardResponse {
		private String rewardType;
		private String rewardCount;
		private String rewardName;
		private String code;
		private LocalDateTime codeExpireDate;

	}

	@Setter
	@Getter
	@Builder
	public static class ProductResponse {
        private String productId;
		private List<CampaignResponse> campaigns;
	}

	public static ParticipateCampaignResponse fromModel(ParticipateCampaignRequest participateCampaignRequest,
			ParticipatedCustomerCampaign availableCampaign){
		return ParticipateCampaignResponse.builder()
				.campaign(fromModel(availableCampaign))
				.customerNumber(participateCampaignRequest.getContactNumber())
				.transactionId(participateCampaignRequest.getTransactionId())
				.updateDate(LocalDateTime.now())
				.build();
	}

	private static CampaignResponse fromModel(ParticipatedCustomerCampaign participatedCustomerCampaign){
		return CampaignResponse.builder()
				.campaignName(participatedCustomerCampaign.getCampaignInformation().getCampaignName())
				.campaignType(participatedCustomerCampaign.getCampaignInformation().getCampaignType().getName())
				.campaignId(participatedCustomerCampaign.getCampaignInformation().getCampaignId())
				.discount(participatedCustomerCampaign.getCampaignReward().getCampaignRewardDiscount()!=null?
						toDiscountResponseModel(participatedCustomerCampaign.getCampaignReward()):null)
				.reward(participatedCustomerCampaign.getCampaignReward().getCampaignRewardGift()!=null?
						toRewardResponseModel(participatedCustomerCampaign.getCampaignReward()):null)
				.build();
	}

	private static RewardResponse toRewardResponseModel(ParticipatedCustomerCampaign.CampaignReward campaignReward){
		return RewardResponse.builder()
				.rewardName(campaignReward.getCampaignRewardGift().getName())
				.rewardType(campaignReward.getCampaignRewardGift().getRewardType())
				.code(campaignReward.getCampaignRewardGift().getGiftCode()!=null?
						campaignReward.getCampaignRewardGift().getGiftCode().getCode():null)
				.codeExpireDate(campaignReward.getCampaignRewardGift().getGiftCode()!=null?
						campaignReward.getCampaignRewardGift().getGiftCode()
								.getGiftCodeInformation().getCodeExpirationDate():null)
				.build();
	}

	private  static RewardDiscountResponse toDiscountResponseModel(ParticipatedCustomerCampaign.CampaignReward campaignReward){
		return RewardDiscountResponse.builder()
				.discountType(campaignReward.getCampaignRewardDiscount()!=null &&
						campaignReward.getCampaignRewardDiscount().getRewardDiscountType()!=null?
						campaignReward.getCampaignRewardDiscount().getRewardDiscountType().getName()
						:null)
				.discountValue(campaignReward.getCampaignRewardDiscount()!=null?
						campaignReward.getCampaignRewardDiscount().getValue()
						:null)
				.code(campaignReward.getCampaignRewardDiscount().getDiscountCode()!=null?
						campaignReward.getCampaignRewardDiscount().getDiscountCode().getCode():null)
				.codeExpireDate(campaignReward.getCampaignRewardDiscount().getDiscountCode()!=null?
						campaignReward.getCampaignRewardDiscount().getDiscountCode()
								.getDiscountCodeInformation().getCodeExpirationDate():null)
				.build();
	}
}
