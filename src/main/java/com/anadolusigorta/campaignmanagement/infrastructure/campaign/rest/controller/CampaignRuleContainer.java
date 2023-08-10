package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.campaign.facade.CampaignRuleFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response.CampaignRuleResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@ApiManagementController
@RequiredArgsConstructor
public class CampaignRuleContainer extends BaseController {

    private final CampaignRuleFacade campaignRuleFacade;

    @GetMapping("campaign-rules")
    public Response<?> getCampaignRulesByRuleId(@RequestParam Long ruleId) {
        return respond(CampaignRuleResponse.fromListOfModel(campaignRuleFacade.getCampaignRulesByRuleId(ruleId)));
    }

}
