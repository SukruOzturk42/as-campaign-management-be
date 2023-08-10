package com.anadolusigorta.campaignmanagement.domain.policyinformation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicyInformation {

    private String policyCode;

    private LocalDate policyStartDate;

    private String policyOwnerContactNumber;

    private String policyNumber;

    private String renewalNumber;

    private String endorsementNumber;

    private String healthPackage;

    private String policyStatus;

    private String policyAgencyNumber;

    private String policyAgencyName;


}
