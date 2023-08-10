package com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignAttribute;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleGiftCodeInformationDetail;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleRewardCampaign;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response.AttributeResponse;
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
public class PolicySaleGiftCodeInformationDetailResponse {

    private Long id;

    private String updater;

    private String description;

    private Long codeCount;

    private LocalDateTime createdAt;

    public static PolicySaleGiftCodeInformationDetailResponse fromModel(PolicySaleGiftCodeInformationDetail policySaleGiftCodeInformationDetail){
        return PolicySaleGiftCodeInformationDetailResponse.builder()
                .id(policySaleGiftCodeInformationDetail.getId())
                .updater(policySaleGiftCodeInformationDetail.getUpdater())
                .description(policySaleGiftCodeInformationDetail.getDescription())
                .codeCount(policySaleGiftCodeInformationDetail.getCodeCount())
                .createdAt(policySaleGiftCodeInformationDetail.getCreatedAt())
                .build();
    }

    public static List<PolicySaleGiftCodeInformationDetailResponse> fromListOfModel(List<PolicySaleGiftCodeInformationDetail> policySaleGiftCodeInformationDetails) {
        return policySaleGiftCodeInformationDetails.stream().map(PolicySaleGiftCodeInformationDetailResponse::fromModel).collect(Collectors.toList());
    }
}
