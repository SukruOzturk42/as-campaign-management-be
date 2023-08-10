package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.campaign.facade.CampaignApprovalFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response.CampaignApprovalResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@ApiManagementController
@RequiredArgsConstructor
public class CampaignApprovalController extends BaseController {

    private final CampaignApprovalFacade campaignApprovalFacade;

    @GetMapping("campaign-approval-history")
    public Response<?> getCampaignApprovalHistory(@RequestParam Long campaignId) {
        return respond(CampaignApprovalResponse
                .fromListOfModel(campaignApprovalFacade.getCampaignApprovalHistoryByCampaignId(campaignId)));
    }



}
