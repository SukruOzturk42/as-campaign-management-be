package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.campaign.facade.CampaignConjunctionFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response.CampaignConjunctionResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@ApiManagementController
@RequiredArgsConstructor
public class CampaignConjunctionController extends BaseController {

    private final CampaignConjunctionFacade campaignConjunctionFacade;

    @GetMapping("campaign-conjunction")
    public Response<?> getCampaignConjunctionsByCompanyId(@RequestParam(value = "companyId") Long companyId) {
        return respond(CampaignConjunctionResponse.fromListOfModel(campaignConjunctionFacade.getCampaignConjunctions(companyId)));
    }

}
