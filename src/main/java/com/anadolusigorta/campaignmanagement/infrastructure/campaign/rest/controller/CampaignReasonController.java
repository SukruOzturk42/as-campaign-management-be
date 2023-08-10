package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.campaign.facade.CampaignReasonFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request.CreateCampaignReasonRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request.DeleteCampaignReasonRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response.CampaignReasonResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@ApiManagementController
@RequiredArgsConstructor
public class CampaignReasonController extends BaseController {

    private final CampaignReasonFacade campaignReasonFacade;

    @GetMapping("campaign-reasons")
    public Response<?> getCampaignReasonsByCampaignId(@RequestParam Long campaignId) {
        return respond(CampaignReasonResponse.fromListOfModel(campaignReasonFacade.getCampaignReasonsByCampaignId(campaignId)));
    }

    @PostMapping("campaign-reasons")
    public Response<?> saveOrUpdateCampaignReason(@Valid @RequestBody CreateCampaignReasonRequest createCampaignReasonRequest) {
        return respond(CampaignReasonResponse
                .fromListOfModel(campaignReasonFacade.saveOrUpdateCampaignReason(createCampaignReasonRequest.toModel())));
    }

    @PostMapping("campaign-reason")
    public Response<?> deleteCampaignReasonByCampaignReason(@RequestBody DeleteCampaignReasonRequest deleteCampaignReasonRequest) {
        return respond(CampaignReasonResponse
                .fromListOfModel(campaignReasonFacade.deleteCampaignReasonByCampaignReason(deleteCampaignReasonRequest.toModel())));
    }

}
