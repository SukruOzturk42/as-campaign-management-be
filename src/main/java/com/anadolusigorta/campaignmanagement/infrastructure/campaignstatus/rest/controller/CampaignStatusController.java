package com.anadolusigorta.campaignmanagement.infrastructure.campaignstatus.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.campaignstatus.facade.CampaignStatusFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignstatus.rest.dto.response.CampaignApprovalStatusResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignstatus.rest.dto.response.CampaignStatusResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Collectors;

@ApiManagementController
@RequiredArgsConstructor
public class CampaignStatusController extends BaseController {

    private final CampaignStatusFacade campaignStatusFacade;

    @GetMapping("campaign-status")
    public Response<?> getCampaignsByCampaignStatus() {
        var activeCampaigns = campaignStatusFacade.getCampaignStatuses();
        return respond(activeCampaigns.stream().map(CampaignStatusResponse::fromModel).collect(Collectors.toList()));
    }

    @GetMapping("campaign-approval-status")
    public Response<?> getApprovalStatuses() {
        var campaigns = campaignStatusFacade.getApprovalStatuses();
        return respond(campaigns.stream().map(CampaignApprovalStatusResponse::fromModel).collect(Collectors.toList()));
    }

}
