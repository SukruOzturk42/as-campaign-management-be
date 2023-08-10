package com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.campaign.facade.CampaignDetailFacade;
import com.anadolusigorta.campaignmanagement.domain.campaign.facade.CampaignFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.response.CampaignDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@ApiController
@RequiredArgsConstructor

public class CampaignDetailDescriptionController extends BaseController {

    private final CampaignDetailFacade campaignDetailFacade;
    private final CampaignFacade campaignFacade;


    @GetMapping("campaign-description")
    public CampaignDetailResponse getCampaignDescription(@RequestParam(value = "campaignId") Long campaignId,
                                            @RequestParam(value = "salesChannel",required = false) String salesChannel) {
        return CampaignDetailResponse.fromModel(campaignFacade.getCampaignInformationByCampaignId(campaignId),
                campaignDetailFacade.getCampaignDetailsByCampaignIdAndSalesChannel(campaignId,salesChannel));
    }

}
