package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request.CampaignCriteriaRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response.*;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.PageableResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.anadolusigorta.campaignmanagement.domain.campaign.facade.CampaignFacade;
import com.anadolusigorta.campaignmanagement.domain.contact.facade.ContactFacade;
import com.anadolusigorta.campaignmanagement.domain.customercampaign.facade.CustomerCampaignFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request.CreateCampaignRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.request.CampaignsSearchRequest;

import lombok.RequiredArgsConstructor;

@ApiManagementController
@RequiredArgsConstructor
@Validated
public class CampaignController extends BaseController {

	private final CampaignFacade campaignFacade;
	private final ContactFacade contactFacade;
	private final CustomerCampaignFacade customerCampaignFacade;

	@PostMapping("cm-campaign/campaign")
	public Response<?> saveCampaign(@Valid @RequestBody CreateCampaignRequest createCampaignRequest) {
		return respond(CampaignResponse.fromModel(campaignFacade.saveCampaign(createCampaignRequest.toModel())));
	}

	@GetMapping("cm-campaign/campaign")
	public Response<?> getCampaignById(@RequestParam(value = "id") Long id) {
		return respond(CampaignResponse.fromModel(campaignFacade.getCampaignById(id)));
	}

	@GetMapping("cm-campaign/customer-campaign")
	public Response<?> getCustomerCampaignById(@RequestParam(value = "id") Long id) {
		return respond(CampaignResponse.fromModel(customerCampaignFacade.getCampaignByCampaignId(id)));
	}

	@GetMapping("version-campaign")
	public Response<?> getCampaignByVersion(@RequestParam(value = "id") Long id,
			@RequestParam(value = "version") Long version) {
		return respond(CampaignResponse.fromModel(campaignFacade.getCampaignByIdAndVersion(id, version)));
	}

	@GetMapping("campaign-action-description")
	public Response<?> getCampaignActionDescriptionById(@RequestParam(value = "id") Long id) {
		return respond(CampaignActionDescriptionResponse
				.fromListOfModel(campaignFacade.getAllCampaignInformationByCampaignId(id)));
	}

	@PostMapping("cm-campaign/campaigns")
	public Response<?> getCampaignsByCampaignSearch(@RequestBody CampaignsSearchRequest campaignsSearchRequest) {
		return respond(CampaignInformationResponse
				.fromListOfModel(contactFacade.searchCampaignInformation(campaignsSearchRequest.toModel())));
	}

	@GetMapping("campaign-versions")
	public Response<?> getSuitableCampaignVersion(@RequestParam(value = "id") Long id) {
		return respond(CampaignVersionResponse.fromListOfModel(campaignFacade.getCampaignVersions(id)));
	}

	@PostMapping("cm-campaign/pageable-customer-campaigns")
	public PageableResponse<?> getPageableCampaigns(@RequestBody @Valid CampaignCriteriaRequest saleCampaignInformationCriteriaRequest,
														@NotNull final Pageable pageable) {
		return  respond(CampaignInformationResponse
				.fromListOfModel(customerCampaignFacade
						.getContactCampaignsInformationPageable(saleCampaignInformationCriteriaRequest.toModel(),pageable)));
	}

}
