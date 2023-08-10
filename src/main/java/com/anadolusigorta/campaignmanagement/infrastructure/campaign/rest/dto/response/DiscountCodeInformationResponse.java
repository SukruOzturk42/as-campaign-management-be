package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.DiscountCodeInformationDetail;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.DiscountCode;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.DiscountCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountCodeInformationResponse {

    private Long id;

    private Long codeTypeId;

    private Long codeKindId;

    private Boolean isPairedWithCustomers;

    private Long codeCharacterLength;

    private LocalDateTime codeExpirationDate;

    private Long codeGeneratedCount;

    private String codeGroupName;

    private String codeTypeName;

    private String fileUploadStatus;

    private Boolean isUsed;

    private List<DiscountCodeInformationDetail> discountCodeInformationDetailList;

    private LocalDateTime uploadStartTime;

    private LocalDateTime uploadFinishTime;

    public static DiscountCodeInformationResponse fromModel(DiscountCodeInformation discountCodeInformation) {
        return DiscountCodeInformationResponse.builder()
                .id(discountCodeInformation.getId())
                .codeGroupName(discountCodeInformation.getCodeGroupName())
                .codeTypeId(discountCodeInformation.getCodeType().getId())
                .codeTypeName(discountCodeInformation.getCodeType().getDescription())
                .codeKindId(discountCodeInformation.getCodeKind().getId())
                .isPairedWithCustomers(discountCodeInformation.getIsPairedWithCustomers())
                .codeCharacterLength(discountCodeInformation.getCodeCharacterLength())
                .codeExpirationDate(discountCodeInformation.getCodeExpirationDate())
                .codeGeneratedCount(discountCodeInformation.getCodeGeneratedCount())
                .fileUploadStatus(discountCodeInformation.getUploadFileStatus())
                .isUsed(discountCodeInformation.getIsUsed())
                .discountCodeInformationDetailList(discountCodeInformation.getDiscountCodeInformationDetails())
                .uploadStartTime(discountCodeInformation.getUploadStartTime())
                .uploadFinishTime(discountCodeInformation.getUploadFinishTime())
                .build();
    }

    public static List<DiscountCodeInformationResponse> fromListOfModel(List<DiscountCodeInformation> discountCodeInformations) {
        return discountCodeInformations.stream().map(DiscountCodeInformationResponse::fromModel).collect(Collectors.toList());
    }

    public static PageContent<DiscountCodeInformationResponse> fromListOfModel(PageContent<DiscountCodeInformation> discountCodeInformations) {
        return PageContent.<DiscountCodeInformationResponse>builder()
                .content(fromListOfModel(discountCodeInformations.getContent()))
                .page(discountCodeInformations.getPage())
                .size(discountCodeInformations.getSize())
                .totalItems(discountCodeInformations.getTotalItems())
                .totalPages(discountCodeInformations.getTotalPages())
                .sort(discountCodeInformations.getSort())
                .build();
    }

}
