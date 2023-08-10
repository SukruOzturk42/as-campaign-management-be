package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.*;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountCodeResponse {

	private Long codeGroupId;

	private String codeGroupName;

	private String codeTypeName;

	private String code;

	private String isActive;

	private String contactNumber;

	private String codeStatus;

	public static DiscountCodeResponse fromModel(DiscountCode discountCode) {
		return DiscountCodeResponse.builder().codeGroupId(discountCode.getDiscountCodeInformation().getId())
				.codeGroupName(discountCode.getDiscountCodeInformation().getCodeGroupName())
				.codeTypeName(discountCode.getDiscountCodeInformation().getCodeType().getDescription())
				.code(discountCode.getCode())
				.isActive(Objects.equals(discountCode.getIsActive(), Boolean.TRUE) ? Constants.YES_TR
						: Constants.NO_TR)
				.contactNumber(discountCode.getContactNumber())
				.codeStatus(discountCode.getCodeStatusEnum() != null
						? getCodeStatusEquivalent(discountCode.getCodeStatusEnum())
						: "")
				.build();
	}

	public static List<DiscountCodeResponse> fromListOfModel(List<DiscountCode> discountCodes) {
		return discountCodes.stream().map(DiscountCodeResponse::fromModel).collect(Collectors.toList());
	}

	public static PageContent<DiscountCodeResponse> fromListOfModel(PageContent<DiscountCode> pageDiscountCodes) {
		return PageContent.<DiscountCodeResponse>builder()
				.content(fromListOfModel(pageDiscountCodes.getContent()))
				.page(pageDiscountCodes.getPage())
				.size(pageDiscountCodes.getSize())
				.totalItems(pageDiscountCodes.getTotalItems())
				.totalPages(pageDiscountCodes.getTotalPages())
				.build();
	}

	private static String getCodeStatusEquivalent(CodeStatusEnum codeStatus) {
		switch (codeStatus) {
		case USED:
			return Constants.USED_CODE_TR;
		case PROPOSED:
			return Constants.PROPOSED_CODE_TR;
		case UNUSED:
		default:
			return Constants.UNUSED_CODE_TR;
		}
	}

}
