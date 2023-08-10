/* odeon_sukruo created on 26.04.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.product.rest.controller */

package com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.contact.facade.ContactFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.request.CampaignDetailRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.request.CampaignsSearchRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiController;
import com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.response.CampaignsResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.response.ContactCampaignResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@ApiController
@RequiredArgsConstructor

@Slf4j
public class ContactCampaignController {

	private final ContactFacade contactFacade;


	@PostMapping("campaigns")
	public CampaignsResponse getCampaignsByCampaignsSearch(@RequestBody CampaignsSearchRequest campaignsSearchRequest) {
		return CampaignsResponse.builder()
				.campaigns(CampaignsResponse.
					fromListOfModel(contactFacade
							.searchCampaignInformation(campaignsSearchRequest.toModel())))
				.build();
	}

	@PostMapping("campaign-detail")
	public ContactCampaignResponse getCampaignsByCampaignSearch(@RequestBody CampaignDetailRequest campaignsSearchRequest) {
		return  ContactCampaignResponse
				.fromModel(contactFacade
						.getAvailableCampaignByCampaignIdAndRuleGroupId(campaignsSearchRequest.getCampaignId(),
								campaignsSearchRequest.getRuleGroupId()));
	}
}
