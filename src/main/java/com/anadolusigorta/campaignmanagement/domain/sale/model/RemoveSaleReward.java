package com.anadolusigorta.campaignmanagement.domain.sale.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RemoveSaleReward {


    private String contactNumber;

    private String policyNumber;

    private Long campaignId;

    private Long ruleGroupId;

    private String description;

    private String policyEndorsementNumber;

}
