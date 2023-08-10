package com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model;

import com.anadolusigorta.campaignmanagement.infrastructure.fileoperation.model.PolicySaleCustomer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePolicySaleRewardCampaign {

    private String campaignName;

    private Long policySaleGiftCodeInformationId;

    private Long giftSendMethodTypeId;

    private List<PolicySaleCustomer> customerList;

    private String fileName;

}
