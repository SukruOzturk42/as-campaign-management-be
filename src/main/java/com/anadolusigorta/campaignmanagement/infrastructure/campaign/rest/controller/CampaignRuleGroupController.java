package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.campaign.facade.CampaignRuleGroupFacade;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRuleGroup;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response.CampaignRuleGroupResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@ApiManagementController
@RequiredArgsConstructor
public class CampaignRuleGroupController extends BaseController {

    private final CampaignRuleGroupFacade campaignRuleGroupFacade;

    @GetMapping("campaign-rule-group")
    public Response<?> getRuleGroupByCampaignId(@RequestParam(value = "campaignId")Long campaignId) {
        var campaignRuleGroups = campaignRuleGroupFacade.getRuleGroupsWithAvailableCodeSetCampaignRuleGroupsByCampaignId(campaignId);
        return respond(CampaignRuleGroupResponse.fromListOfModel(campaignRuleGroups));
    }

    @GetMapping("campaign-rule-groups")
    public List<String> getRuleGroupNamesByCustomerCampaignId(@RequestParam Long campaignId) {
        return campaignRuleGroupFacade.getCampaignRuleGroupNamesByCampaignId(campaignId);
    }

}
