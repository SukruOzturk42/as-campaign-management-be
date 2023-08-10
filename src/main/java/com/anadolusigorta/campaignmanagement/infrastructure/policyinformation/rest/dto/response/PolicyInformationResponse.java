package com.anadolusigorta.campaignmanagement.infrastructure.policyinformation.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.domain.policyinformation.model.PolicyInformation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicyInformationResponse {

    private String policyCode;

    private LocalDate policyStartDate;

    private String policyOwnerContactNumber;

    private String policyNumber;

    private String policyAgencyNumber;

    private String policyAgencyName;

    private String renewalNumber;

    private String endorsementNumber;

    private String healthPackage;

    private String policyStatus;


    public static PolicyInformationResponse fromModel(PolicyInformation policyInformation) {
        return PolicyInformationResponse.builder()
                .policyNumber(policyInformation.getPolicyNumber())
                .policyAgencyNumber(policyInformation.getPolicyAgencyNumber())
                .policyAgencyName(policyInformation.getPolicyAgencyName())
                .policyCode(policyInformation.getPolicyCode())
                .endorsementNumber(policyInformation.getEndorsementNumber())
                .healthPackage(policyInformation.getHealthPackage())
                .policyOwnerContactNumber(policyInformation.getPolicyOwnerContactNumber())
                .policyStartDate(policyInformation.getPolicyStartDate())
                .policyStatus(Objects.equals(policyInformation.getPolicyStatus(), "40") ?
                        Constants.POLICY_STATUS_CANCELED : Constants.POLICY_STATUS_ACTIVE)
                .renewalNumber(policyInformation.getRenewalNumber())
                .build();
    }

    public static List<PolicyInformationResponse> fromListOfModel(List<PolicyInformation> policyInformation) {
        return policyInformation.stream().map(PolicyInformationResponse::fromModel).collect(Collectors.toList());
    }

}
