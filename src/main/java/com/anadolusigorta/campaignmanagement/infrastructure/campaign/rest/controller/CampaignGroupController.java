package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.campaign.facade.CampaignGroupFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request.CreateCampaignGroupRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response.CampaignGroupResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@ApiManagementController
@RequiredArgsConstructor
public class CampaignGroupController extends BaseController {

    private final CampaignGroupFacade campaignGroupFacade;

    @GetMapping("campaign-group")
    public Response<?> getCampaignGroups() {
        return respond(CampaignGroupResponse.fromListOfModel(campaignGroupFacade.getCampaignGroups()));
    }

    @PostMapping("campaign-group")
    public Response<?> createCampaignGroup(@RequestBody CreateCampaignGroupRequest createCampaignGroupRequest) {
        return respond(CampaignGroupResponse.fromModel(campaignGroupFacade.createCampaignGroup(createCampaignGroupRequest.toModel())));
    }
}
