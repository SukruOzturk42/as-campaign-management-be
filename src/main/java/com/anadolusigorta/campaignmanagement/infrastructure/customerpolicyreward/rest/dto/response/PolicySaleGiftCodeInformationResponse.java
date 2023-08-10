package com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.GiftCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleGiftCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleGiftCodeInformationDetail;
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
public class PolicySaleGiftCodeInformationResponse {

    private Long id;

    private Long rewardCompanyInformationId;

    private String companyInformationName;

    private Long totalCodeCount;

    private Long usedCodeCount;

    private Long remainingCodeCount;

    private String fileUploadEnum;

    private Long rewardGiftTicketTypeId;

    private String rewardGiftTicketTypeName;

    private List<PolicySaleGiftCodeInformationDetailResponse> policySaleGiftCodeInformationDetails;

    public static PolicySaleGiftCodeInformationResponse fromModel(PolicySaleGiftCodeInformation policySaleGiftCodeInformation) {
        return PolicySaleGiftCodeInformationResponse.builder()
                .id(policySaleGiftCodeInformation.getId())
                .rewardCompanyInformationId(policySaleGiftCodeInformation.getRewardCompanyInformation().getId())
                .companyInformationName(policySaleGiftCodeInformation.getRewardCompanyInformation().getDescription())
                .totalCodeCount(policySaleGiftCodeInformation.getTotalCodeCount() != null ? policySaleGiftCodeInformation.getTotalCodeCount() : 0)
                .usedCodeCount(policySaleGiftCodeInformation.getUsedCodeCount() != null ? policySaleGiftCodeInformation.getUsedCodeCount() : 0)
                .remainingCodeCount(remainingCodes(policySaleGiftCodeInformation))
                .rewardGiftTicketTypeId(policySaleGiftCodeInformation.getRewardGiftTicketType().getId())
                .rewardGiftTicketTypeName(policySaleGiftCodeInformation.getRewardGiftTicketType().getName())
                .fileUploadEnum(policySaleGiftCodeInformation.getUploadFileEnum())
                .policySaleGiftCodeInformationDetails(PolicySaleGiftCodeInformationDetailResponse.fromListOfModel(policySaleGiftCodeInformation.getPolicySaleGiftCodeInformationDetails()))
                .build();
    }

    public static List<PolicySaleGiftCodeInformationResponse> fromListModel(List<PolicySaleGiftCodeInformation> policySaleGiftCodeInformations) {
        return policySaleGiftCodeInformations.stream()
                .map(PolicySaleGiftCodeInformationResponse::fromModel)
                .collect(Collectors.toList());
    }

    public static Long remainingCodes(PolicySaleGiftCodeInformation policySaleGiftCodeInformation) {
        return policySaleGiftCodeInformation.getTotalCodeCount() != null && policySaleGiftCodeInformation.getUsedCodeCount() != null ?
                policySaleGiftCodeInformation.getTotalCodeCount() - policySaleGiftCodeInformation.getUsedCodeCount() :
                0;
    }

}

