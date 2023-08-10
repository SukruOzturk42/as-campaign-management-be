package com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftSendMethodType;
import com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.entity.PolicySaleCustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicySaleRewardCampaign {

    private Long id;
    private String campaignName;
    private String customerFileName;
    private PolicySaleGiftCodeInformation policySaleGiftCodeInformation;
    private RewardGiftSendMethodType rewardGiftSendMethodType;
    private List<PolicySaleCustomer> policySaleCustomers;
    private int totalCustomer;
    private int unassignedCustomerCount;
    private LocalDateTime createDate;

}
