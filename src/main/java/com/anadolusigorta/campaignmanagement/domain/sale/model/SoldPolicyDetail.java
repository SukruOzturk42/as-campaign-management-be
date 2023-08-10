package com.anadolusigorta.campaignmanagement.domain.sale.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SoldPolicyDetail {

    private String policyNumber;

    private LocalDateTime policyStartDate;

    private LocalDateTime policyCreateDate;

    private String newPolicy;

    private String proposalNumber;

    private String revisionNumber;

    private float grossPremium;

    private float netPremium;

    private String discountValue;

}
