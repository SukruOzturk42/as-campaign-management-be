package com.anadolusigorta.campaignmanagement.infrastructure.sale.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.sale.model.SaleReport;
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
public class SaleReportResponse {


	private Long campaignId;

	private Long campaignVersion;

	private String campaignName;

	private String contactNumber;

	private String rewardOwnerContactNumber;


	private String ruleGroupName;

	private String policyNumber;

	private String proposalNumber;


	private LocalDateTime createDate;

	private LocalDateTime sendDate;


	private String sendType;


	private String sendStatus;

	private String rewardDescription;

	private String discount;

	private String discountCode;

	private String giftCode;





	public static SaleReportResponse fromModel(SaleReport saleInformation) {
		return SaleReportResponse.builder()
				.campaignId(saleInformation.getCampaignId())
				.campaignName(saleInformation.getCampaignName())
				.contactNumber(saleInformation.getContactNumber())
				.campaignVersion(saleInformation.getCampaignVersion())
				.rewardOwnerContactNumber(saleInformation.getRewardOwnerContactNumber())
				.ruleGroupName(saleInformation.getRuleGroupName())
				.policyNumber(saleInformation.getPolicyNumber())
				.proposalNumber(saleInformation.getProposalNumber())
				.createDate(saleInformation.getCreateDate())
				.sendDate(saleInformation.getSendDate())
				.sendType(saleInformation.getSendType())
				.sendStatus(saleInformation.getSendStatus())
				.rewardDescription(saleInformation.getRewardDescription())
				.discount(saleInformation.getDiscount())
				.discountCode(saleInformation.getDiscountCode())
				.giftCode(saleInformation.getGiftCode())
				.build();
	}


	public static PageContent<SaleReportResponse> fromListOfModel(PageContent<SaleReport> pageContent) {

		return PageContent.<SaleReportResponse>builder()
				.content(pageContent.getContent().stream()
						.map(SaleReportResponse::fromModel)
						.collect(Collectors.toList()))
				.page(pageContent.getPage()).size(pageContent.getSize()).totalItems(pageContent.getTotalItems())
				.build();
	}

	public static List<SaleReportResponse> fromListOfModel(List<SaleReport> sales) {

		return sales.stream()
						.map(SaleReportResponse::fromModel)
						.collect(Collectors.toList());

	}





}
