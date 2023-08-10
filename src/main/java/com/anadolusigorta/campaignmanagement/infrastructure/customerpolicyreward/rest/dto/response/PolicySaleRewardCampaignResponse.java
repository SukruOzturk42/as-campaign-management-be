package com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleRewardCampaign;
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
public class PolicySaleRewardCampaignResponse {

    private Long id;
    private String campaignName;
    private String customerFileName;
    private String policySaleGiftCodeInformation;
    private String rewardGiftSendMethodType;
    private int totalCustomer;
    private int unassignedCustomerCount;
    private LocalDateTime createDate;

    public static PolicySaleRewardCampaignResponse fromModel(PolicySaleRewardCampaign policySaleRewardCampaign){
        return PolicySaleRewardCampaignResponse.builder()
                .campaignName(policySaleRewardCampaign.getCampaignName())
                .policySaleGiftCodeInformation(policySaleRewardCampaign.getPolicySaleGiftCodeInformation().getRewardCompanyInformation().getDescription()
                + " - "+policySaleRewardCampaign.getPolicySaleGiftCodeInformation().getRewardGiftTicketType().getName() )
                .rewardGiftSendMethodType(policySaleRewardCampaign.getRewardGiftSendMethodType().getDescription())
                .totalCustomer(policySaleRewardCampaign.getTotalCustomer())
                .unassignedCustomerCount(policySaleRewardCampaign.getUnassignedCustomerCount())
                .id(policySaleRewardCampaign.getId())
                .createDate(policySaleRewardCampaign.getCreateDate())
                .customerFileName(policySaleRewardCampaign.getCustomerFileName())
                .build();
    }

    public static List<PolicySaleRewardCampaignResponse> fromListModel(List<PolicySaleRewardCampaign> policySaleRewardCampaigns){
        return policySaleRewardCampaigns.stream()
                .map(PolicySaleRewardCampaignResponse::fromModel)
                .collect(Collectors.toList());
    }
}
