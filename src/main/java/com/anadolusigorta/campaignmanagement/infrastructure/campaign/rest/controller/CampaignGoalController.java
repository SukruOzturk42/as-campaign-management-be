package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.campaigngoal.facade.CampaignGoalFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request.CreateCampaignGoalRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request.DeleteCampaignGoalRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response.CampaignGoalResponse;
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
public class CampaignGoalController extends BaseController {

    private final CampaignGoalFacade campaignGoalFacade;

    @GetMapping("campaign-goals")
    public Response<?> getCampaignGoalByCampaignId(@RequestParam Long campaignId) {
        return respond(CampaignGoalResponse.fromListOfModel(campaignGoalFacade.getCampaignGoalsByCampaignId(campaignId)));
    }

    @PostMapping("campaign-goals")
    public Response<?> saveOrUpdateCampaignGoal(@Valid @RequestBody CreateCampaignGoalRequest createCampaignGoalRequest) {
        return respond(CampaignGoalResponse
                .fromListOfModel(campaignGoalFacade.saveOrUpdateCampaignGoal(createCampaignGoalRequest.toModel())));
    }

    @PostMapping("campaign-goal")
    public Response<?> deleteCampaignGoalByCampaignGoal(@RequestBody DeleteCampaignGoalRequest deleteCampaignGoalRequest) {
        return respond(CampaignGoalResponse
                .fromListOfModel(campaignGoalFacade.deleteCampaignGoalByCampaignGoal(deleteCampaignGoalRequest.toModel())));
    }

}
