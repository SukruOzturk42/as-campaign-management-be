package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeStatusEnum;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.GiftCode;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.PageableResponse;
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
public class GiftCodeResponse {

	private Long codeGroupId;

	private String codeGroupName;

	private String codeTypeName;

	private String code;

	private String contactNumber;

	private String codeStatus;

	private String link;

	public static GiftCodeResponse fromModel(GiftCode giftCode) {
		return GiftCodeResponse.builder().codeGroupId(giftCode.getGiftCodeInformation().getId())
				.codeGroupName(giftCode.getGiftCodeInformation().getRewardCompanyInformation().getName() + " "
						+ giftCode.getGiftCodeInformation().getRewardGiftTicketType().getName())
				.codeTypeName("")
				.code(giftCode.getCode()).contactNumber(giftCode.getContactNumber())
				.codeStatus(giftCode.getCodeStatusEnum() != null
						? getCodeStatusEquivalent(giftCode.getCodeStatusEnum())
						: "")
				.link(giftCode.getQrCodeUrl())
				.build();
	}

	public static List<GiftCodeResponse> fromListOfModel(List<GiftCode> giftCodes) {
		return giftCodes.stream().map(GiftCodeResponse::fromModel).collect(Collectors.toList());
	}

	public static PageContent<GiftCodeResponse> fromListOfModel(PageContent<GiftCode> pageGiftCodes) {
		return PageContent.<GiftCodeResponse>builder()
				.content(fromListOfModel(pageGiftCodes.getContent()))
				.page(pageGiftCodes.getPage())
				.size(pageGiftCodes.getSize())
				.totalItems(pageGiftCodes.getTotalItems())
				.totalPages(pageGiftCodes.getTotalPages())
				.build();


	}


	private static String getCodeStatusEquivalent(CodeStatusEnum codeStatus) {
		switch (codeStatus) {
			case PROPOSED:
				return Constants.PROPOSED_CODE_TR;
			case USED:
				return Constants.USED_CODE_TR;
			case UNUSED:
			default:
				return Constants.UNUSED_CODE_TR;
		}
	}

}
