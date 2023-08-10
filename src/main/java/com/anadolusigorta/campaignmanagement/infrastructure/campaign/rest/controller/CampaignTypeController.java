package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.campaign.facade.CampaignTypeFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response.CampaignTypeResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@ApiManagementController
@RequiredArgsConstructor
public class CampaignTypeController extends BaseController {
    private final CampaignTypeFacade campaignTypeFacade;

    @GetMapping("campaign-types/{companyId}")
    public Response<?> getCampaignTypesByCompanyId(@PathVariable(value="companyId") Long companyId) {
        return respond(CampaignTypeResponse.fromListOfModel(campaignTypeFacade.getCampaignTypesByCompanyId(companyId)));
    }

    @GetMapping("campaign-types")
    public Response<?> getCampaignTypes() {
        return respond(CampaignTypeResponse.fromListOfModel(campaignTypeFacade.getCampaignTypes()));
    }
}
