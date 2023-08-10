package com.anadolusigorta.campaignmanagement.infrastructure.cache.rest;

import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response.CampaignResponse;
import org.springframework.web.bind.annotation.GetMapping;

import com.anadolusigorta.campaignmanagement.domain.cache.facade.CustomerCampaignRedisCacheFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;

import lombok.RequiredArgsConstructor;

@ApiController
@RequiredArgsConstructor
public class CacheController extends BaseController {
	private final CustomerCampaignRedisCacheFacade customerCampaignRedisCacheFacade;

	@GetMapping("cache-campaigns")
	public Response<?> reloadCachedCampaigns() {
		return respond(CampaignResponse
				.fromListOfModel(customerCampaignRedisCacheFacade.reloadCustomerCampaignCache()));
	}

}
