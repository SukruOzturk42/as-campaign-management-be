package com.anadolusigorta.campaignmanagement.infrastructure.sale.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.GiftCode;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.sale.model.SaleInformation;
import com.anadolusigorta.campaignmanagement.domain.sale.model.SaleReport;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionOperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleInformationResponse {

	private Long campaignVersion;
	private String contactNumber;
	private LocalDateTime createDate;
	private String saleTransactionOperationType;
	private String policyNumber;
	private String ruleGroupName;
	private Long ruleGroupId;
	private String discount;
	private String rewardGiftName;
	private String giftCode;
	private String discountCode;
	private LocalDateTime joinDate;
	private LocalDateTime deliveryDate;
	private String deliverySendMethodType;
	private String deliveryStatus;
	private String notificationDeliveryDescription;
	private String proposalNumber;
	private String rewardOwnerContactNo;
	private SaleTransactionOperationType requestType;

	public static SaleInformationResponse fromModel(SaleInformation saleInformation) {
		return SaleInformationResponse.builder().contactNumber(saleInformation.getContactNumber())
				.createDate(saleInformation.getCreateDate())
				.saleTransactionOperationType(saleInformation.getRequestType().getValue())
				.policyNumber(saleInformation.getSoldPolicyDetail().getPolicyNumber())
				.ruleGroupName(saleInformation.getCampaignRuleGroup().getName())
				.ruleGroupId(saleInformation.getCampaignRuleGroup().getRuleGroupId())
				.discount(saleInformation.getSoldPolicyDetail().getDiscountValue())
				.proposalNumber(saleInformation.getSoldPolicyDetail().getProposalNumber())
				.giftCode(getGiftCode(saleInformation))
				.campaignVersion(saleInformation.getCampaignInformation().getVersion())
				.deliveryDate(saleInformation.getSaleRewardGift() != null
						? saleInformation.getSaleRewardGift().getRewardSendDate()
						: null)
				.discountCode(saleInformation.getCampaignCode())
				.deliverySendMethodType(
						saleInformation.getCampaignRuleGroup().getCampaignReward().getCampaignRewardGift() != null
								? saleInformation.getCampaignRuleGroup().getCampaignReward().getCampaignRewardGift()
										.getRewardGiftSendMethodType().getName()
								: null)
				.deliveryStatus(saleInformation.getSaleRewardGift() != null
						&& saleInformation.getSaleRewardGift().getRewardNotificationStatus() != null
								? saleInformation.getSaleRewardGift().getRewardNotificationStatus().getDescription()
								: null)
				.notificationDeliveryDescription(saleInformation.getSaleRewardGift() != null
						? saleInformation.getSaleRewardGift().getNotificationDeliveryDescription()
						: null)
				.proposalNumber(saleInformation.getSoldPolicyDetail().getProposalNumber())
				.rewardGiftName(getGiftOrGiftCodeName(saleInformation))
				.rewardOwnerContactNo(getContactNo(saleInformation)).build();
	}


	private static String getContactNo(SaleInformation saleInformation) {
		return saleInformation.getSaleRewardGift() != null
				? saleInformation.getSaleRewardGift().getRewardOwnerContactNo()
				: null;
	}

	private static String getGiftCode(SaleInformation saleInformation) {
		if(saleInformation.getSaleRewardGift() != null
				&& saleInformation.getSaleRewardGift().getGiftCode() != null){
			return saleInformation.getSaleRewardGift().getGiftCode().getCode();
		}
		else if(saleInformation.getSaleRewardGift() != null
				&& saleInformation.getSaleRewardGift().getGiftCodes() != null){
			return saleInformation.getSaleRewardGift().getGiftCodes()
					.stream()
					.map(GiftCode::getCode)
					.collect(Collectors.joining(","));
		}
	  return null;
	}

	private static String getGiftOrGiftCodeName(SaleInformation saleInformation) {
		if (saleInformation.getCampaignRuleGroup().getCampaignReward().getCampaignRewardGift() != null) {
			if (saleInformation.getCampaignRuleGroup().getCampaignReward().getCampaignRewardGift()
					.getRewardGiftProduct() != null)
				return saleInformation.getCampaignRuleGroup().getCampaignReward().getCampaignRewardGift()
						.getRewardGiftProduct().getName();
			else if (saleInformation.getCampaignRuleGroup().getCampaignReward().getCampaignRewardGift()
					.getGiftCodeInformation() != null)
				return saleInformation.getCampaignRuleGroup().getCampaignReward().getCampaignRewardGift()
						.getGiftCodeInformation().getRewardCompanyInformation().getName() + " - "
						+ saleInformation.getCampaignRuleGroup().getCampaignReward().getCampaignRewardGift()
								.getGiftCodeInformation().getRewardGiftTicketType().getName();
		}
		return null;
	}


	public static List<SaleInformationResponse> toListFromListOfModel(List<SaleInformation> saleInformations) {
		return saleInformations.stream().map(SaleInformationResponse::fromModel).collect(Collectors.toList());
	}



}
