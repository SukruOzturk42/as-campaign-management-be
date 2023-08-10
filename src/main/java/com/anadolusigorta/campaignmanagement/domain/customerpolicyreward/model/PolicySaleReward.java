package com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicySaleReward {

    private  String code;
    private  String policyNumber;

}
