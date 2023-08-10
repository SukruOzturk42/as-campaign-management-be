package com.anadolusigorta.campaignmanagement.infrastructure.campaigngoal.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.campaigngoal.facade.CampaignPolicyTypeFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigngoal.rest.dto.response.CampaignPolicyTypeResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@ApiManagementController
@RequiredArgsConstructor
public class CampaignPolicyTypeController extends BaseController {

    private final CampaignPolicyTypeFacade campaignPolicyTypeFacade;

    @GetMapping("campaign-policy-type")
    public Response<?> findAllCampaignPolicyType() {
        return respond(CampaignPolicyTypeResponse.fromListOfModel(campaignPolicyTypeFacade.findAllCampaignPolicyType()));
    }

}
