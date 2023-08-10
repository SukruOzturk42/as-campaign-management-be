package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.DiscountCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.GiftCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleGiftCodeInformation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GiftCodeInformationResponse {

    private Long id;

    private Long companyInformationId;

    private String companyInformationName;

    private Long totalCodeCount;

    private Long usedCodeCount;

    private Long remainingCodeCount;

    private String fileUploadEnum;

    private Long rewardGiftTicketTypeId;

    private String rewardGiftTicketTypeName;

    public static GiftCodeInformationResponse fromModel(GiftCodeInformation giftCodeInformation) {
        return GiftCodeInformationResponse.builder()
                .id(giftCodeInformation.getId())
                .companyInformationId(giftCodeInformation.getRewardCompanyInformation().getId())
                .companyInformationName(giftCodeInformation.getRewardCompanyInformation().getDescription())
                .totalCodeCount(giftCodeInformation.getTotalCodeCount())
                .usedCodeCount(giftCodeInformation.getUsedCodeCount())
                .remainingCodeCount(remainingCodes(giftCodeInformation))
                .rewardGiftTicketTypeId(giftCodeInformation.getRewardGiftTicketType().getId())
                .rewardGiftTicketTypeName(giftCodeInformation.getRewardGiftTicketType().getName())
                .fileUploadEnum(giftCodeInformation.getUploadFileEnum())
                .build();
    }

    public static List<GiftCodeInformationResponse> fromListModel(List<GiftCodeInformation> giftCodesInformation) {
        return giftCodesInformation.stream()
                .map(GiftCodeInformationResponse::fromModel)
                .collect(Collectors.toList());
    }

    public static Long remainingCodes(GiftCodeInformation giftCodeInformation) {
        return giftCodeInformation.getTotalCodeCount() != null && giftCodeInformation.getUsedCodeCount() != null ?
                giftCodeInformation.getTotalCodeCount() - giftCodeInformation.getUsedCodeCount() :
                0;
    }

    public static PageContent<GiftCodeInformationResponse> fromListOfModel(PageContent<GiftCodeInformation> giftCodeInfo) {
        return PageContent.<GiftCodeInformationResponse>builder()
                .content(fromListModel(giftCodeInfo.getContent()))
                .page(giftCodeInfo.getPage())
                .size(giftCodeInfo.getSize())
                .sort(giftCodeInfo.getSort())
                .totalItems(giftCodeInfo.getTotalItems())
                .totalPages(giftCodeInfo.getTotalPages())
                .build();
    }

}
