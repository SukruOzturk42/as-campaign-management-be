package com.anadolusigorta.campaignmanagement.infrastructure.campaignsalechannel.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.campaignsalechannel.facade.CampaignSaleChannelTypeFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignsalechannel.rest.dto.response.CampaignSaleChannelTypeResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@ApiManagementController
@RequiredArgsConstructor
public class CampaignSaleChannelTypeController extends BaseController {

    private final CampaignSaleChannelTypeFacade campaignSaleChannelTypeFacade;

    @GetMapping("sale-channel-type")
    public Response<?> getAllCampaignSaleChannelTypes() {
        return respond(CampaignSaleChannelTypeResponse.fromListOfModel(campaignSaleChannelTypeFacade.findAllCampaignSaleChannelTypes()));
    }

}
