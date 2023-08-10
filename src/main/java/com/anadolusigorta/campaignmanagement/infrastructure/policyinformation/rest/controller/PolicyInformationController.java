package com.anadolusigorta.campaignmanagement.infrastructure.policyinformation.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.policyinformation.facade.PolicyInformationFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import com.anadolusigorta.campaignmanagement.infrastructure.policyinformation.rest.dto.response.PolicyInformationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@ApiManagementController
@RequiredArgsConstructor
public class PolicyInformationController extends BaseController {

    private final PolicyInformationFacade policyInformationFacade;

    @GetMapping("latest-policy-information")
    public Response<?> getPolicyInformation(@RequestParam(value = "policyNumber") String policyNumber) {
        return respond(PolicyInformationResponse.fromModel(policyInformationFacade
                .getLatestPolicyInformationByPolicyNumber(policyNumber)));
    }
}
