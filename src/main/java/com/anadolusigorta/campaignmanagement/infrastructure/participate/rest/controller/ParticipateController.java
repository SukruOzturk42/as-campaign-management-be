/* odeon_sukruo created on 26.04.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.product.rest.controller */

package com.anadolusigorta.campaignmanagement.infrastructure.participate.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.participate.facade.ParticipateFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.participate.rest.dto.request.ParticipateCampaignRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.participate.rest.dto.response.ParticipateCampaignResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@ApiController
@RequiredArgsConstructor
public class ParticipateController extends BaseController {

	private final ParticipateFacade participateFacade;


	@PostMapping("participate")
	public ParticipateCampaignResponse participateCampaign(@RequestBody @Valid ParticipateCampaignRequest customerCampaignRequest) {
		return ParticipateCampaignResponse.fromModel(customerCampaignRequest,participateFacade
				.participateCampaign(customerCampaignRequest.toModel()));

	}
}
