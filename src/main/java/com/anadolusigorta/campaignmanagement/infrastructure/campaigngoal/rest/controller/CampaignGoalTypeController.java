package com.anadolusigorta.campaignmanagement.infrastructure.campaigngoal.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.campaigngoal.facade.CampaignGoalTypeFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigngoal.rest.dto.response.CampaignGoalTypeResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@ApiManagementController
@RequiredArgsConstructor
public class CampaignGoalTypeController extends BaseController {
    private final CampaignGoalTypeFacade campaignGoalTypeFacade;

    @GetMapping(value = "goal-type")
    public Response<?> findAllCampaignGoalTypes() {
        return respond(CampaignGoalTypeResponse.fromListOfModel(campaignGoalTypeFacade.findAllCampaignGoalTypes()));
    }
}
