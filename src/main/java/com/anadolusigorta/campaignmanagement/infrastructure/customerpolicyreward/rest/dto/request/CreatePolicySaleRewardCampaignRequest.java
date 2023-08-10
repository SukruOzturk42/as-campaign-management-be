package com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.CreatePolicySaleRewardCampaign;
import com.anadolusigorta.campaignmanagement.infrastructure.fileoperation.model.PolicySaleCustomer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePolicySaleRewardCampaignRequest {

    private String campaignName;

    private Long policySaleGiftCodeInformationId;

    private Long giftSendMethodTypeId;

    private String fileName;

    private List<PolicySaleCustomer> customerList = new ArrayList<>();

    public CreatePolicySaleRewardCampaign toModel() {
        return CreatePolicySaleRewardCampaign.builder()
                .campaignName(campaignName)
                .policySaleGiftCodeInformationId(policySaleGiftCodeInformationId)
                .giftSendMethodTypeId(giftSendMethodTypeId)
                .customerList(customerList)
                .fileName(fileName != null ? fileName : null)
                .build();
    }
}
