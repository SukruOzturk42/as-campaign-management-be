package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.campaign.facade.CampaignDetailFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request.CreateCampaignDetailRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request.DeleteCampaignDetailRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response.CampaignDetailResponse;
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
public class CampaignDetailController extends BaseController {

    private final CampaignDetailFacade campaignDetailFacade;

    @GetMapping("campaign-details")
    public Response<?> getCampaignGoalByCampaignId(@RequestParam Long campaignId) {
        return respond(CampaignDetailResponse.fromListOfModel(campaignDetailFacade.getCampaignDetailsByCampaignId(campaignId)));
    }

    @PostMapping("campaign-details")
    public Response<?> saveOrUpdateCampaignDetail(@Valid @RequestBody CreateCampaignDetailRequest createCampaignDetailRequest) {
        return respond(CampaignDetailResponse
                .fromListOfModel(campaignDetailFacade.saveOrUpdateCampaignDetail(createCampaignDetailRequest.toModel())));
    }

    @PostMapping("campaign-detail")
    public Response<?> deleteCampaignDetailByCampaignDetail(@RequestBody DeleteCampaignDetailRequest deleteCampaignDetailRequest) {
        return respond(CampaignDetailResponse
                .fromListOfModel(campaignDetailFacade.deleteCampaignDetailByCampaignDetail(deleteCampaignDetailRequest.toModel())));
    }

    @GetMapping("campaign-image")
    public Response<?> getCampaignImageFromCM(@RequestParam Long campaignDetailId) {
        return respond(campaignDetailFacade.getCampaignImageFromCM(campaignDetailId));
    }

}
