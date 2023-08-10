/* dks20165 created on 16.06.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response */

package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignReward;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRuleAttribute;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeKind;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeType;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.DiscountCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftSendMethodType;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request.CreateCampaignRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public class RuleGroupRewardResponse {

		private Long rewardId;
		private RuleGroupRewardDiscountResponse discount;
		private RuleGroupRewardGiftResponse gift;

		@Data
		@Builder
		@AllArgsConstructor
		@NoArgsConstructor
		public static class RuleGroupRewardDiscountResponse {

			private Long rewardDiscountId;
			private String value;
			private String name;
			private Long discountTypeId;
			private Long discountDetailTypeId;
			private Long discountKindId;
			private Long discountRoleId;
			private Long discountCodeInformationId;
			private String coverCodeDiscountValue;
			private Long coverCodeTypeId;
		}


		@Data
		@Builder
		@AllArgsConstructor
		@NoArgsConstructor
		public static class RuleGroupRewardGiftResponse {
			private Long rewardGiftId;
			private String value;
			private String name;
			private Long totalProductCount;
			private Long totalCustomerProductCount;
			private Long customerCount;
			private Long rewardGiftTypeId;
			private String rewardGiftTypeName;
			private Long rewardGiftDeliveryTypeId;
			private String rewardGiftDeliveryTypeName;
			private Long rewardGiftPaymentTypeId;
			private Long rewardGiftDeliveryStartTypeId;
			private Long rewardGifKindId;
			private Long productDeliveryOrder;
			private Long rewardGiftCodeInformationId;
			private String rewardGiftCodeInformationName;
			private Long rewardRoleId;

			@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
			@JsonSerialize(using = LocalDateTimeSerializer.class)
			private LocalDateTime lastSendTime;

			private Integer dayAfterDeliveryStart;

			private Long sendMethodTypeId;

			private Long rewardProductId;

			private String rewardGiftTemplateId;

			private List<CampaignRuleAttribute> sendRules;

		}

		public static RuleGroupRewardResponse fromModel(CampaignReward campaignReward){
			return RuleGroupRewardResponse.builder()
					.rewardId(campaignReward.getRewardId())
					.discount(campaignReward.getCampaignRewardDiscount()!=null?
							fromModel(campaignReward.getCampaignRewardDiscount())
							:null)
					.gift(campaignReward.getCampaignRewardGift()!=null ?
							fromModel(campaignReward.getCampaignRewardGift()):
							null)
					.build();
		}



		private static RuleGroupRewardDiscountResponse fromModel(CampaignReward.CampaignRewardDiscount campaignReward){
		return RuleGroupRewardDiscountResponse.builder()
				.rewardDiscountId(campaignReward.getRewardDiscountId())
				.name(campaignReward.getName())
				.discountTypeId(campaignReward.getRewardDiscountType()!=null?
						campaignReward.getRewardDiscountType().getId()
						:null)
				.discountDetailTypeId(campaignReward.getRewardDiscountDetailType()!=null?
						campaignReward.getRewardDiscountDetailType().getId():null)
				.discountKindId(campaignReward.getRewardDiscountKind()!=null?
						campaignReward.getRewardDiscountKind().getId():
						null)
				.value(campaignReward.getValue())
				.discountCodeInformationId(campaignReward.getDiscountCodeInformation()!=null?
						campaignReward.getDiscountCodeInformation().getId():null)
				.coverCodeTypeId(campaignReward.getCoverCodeType()!=null?campaignReward.getCoverCodeType().getId():null)
				.coverCodeDiscountValue(campaignReward.getCoverCodeDiscountValue())
				.build();
	}

		private static RuleGroupRewardGiftResponse fromModel(CampaignReward.CampaignRewardGift gift) {
			return RuleGroupRewardGiftResponse.builder()
					.rewardGiftId(gift.getRewardGiftId())
					.rewardGifKindId(gift.getRewardGiftKind()!=null?
							gift.getRewardGiftKind().getId()
							:null)
					.rewardGiftTypeId(gift.getRewardGiftType()!=null?
							gift.getRewardGiftType().getId()
							:null)
					.rewardGiftTypeName(gift.getRewardGiftType()!=null?
							gift.getRewardGiftType().getName()
							:null)
					.rewardGiftPaymentTypeId(gift.getRewardGiftPaymentType()!=null?
							gift.getRewardGiftPaymentType().getId()
							:null)
					.rewardGiftDeliveryStartTypeId(gift.getRewardGiftDeliveryStartType()!=null ?
							gift.getRewardGiftDeliveryStartType().getId()
							:null)
					.rewardGiftDeliveryTypeName(gift.getRewardGiftDeliveryStartType()!=null ?
							gift.getRewardGiftDeliveryStartType().getName()
							:null)
					.rewardGiftDeliveryTypeId(gift.getRewardGiftDeliveryType()!=null?
							gift.getRewardGiftDeliveryType().getId()
							:null)
					.customerCount(gift.getCustomerCount())
					.totalCustomerProductCount(gift.getCustomerProductCount())
					.totalProductCount(gift.getTotalProductCount())
					.value(gift.getValue())
					.productDeliveryOrder(gift.getProductDeliveryOrder())
					.rewardGiftCodeInformationId(gift.getGiftCodeInformation()!=null?gift.getGiftCodeInformation().getId():null)
					.dayAfterDeliveryStart(gift.getDayAfterDeliveryStart())
					.lastSendTime(gift.getLastSendTime())
					.sendMethodTypeId(gift.getRewardGiftSendMethodType()!=null?
							gift.getRewardGiftSendMethodType().getId():null)
					.rewardGiftTemplateId(gift.getRewardGiftTemplateId())
					.rewardProductId(gift.getRewardProductId())
					.rewardRoleId(gift.getRewardRole()!=null?gift.getRewardRole().getId():null)
					.sendRules(gift.getSendRules()!=null?gift.getSendRules():null)
					.build();
		}
}
