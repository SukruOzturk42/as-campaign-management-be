/* odeon_sukruo created on 29.04.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.controller */

package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.campaign.facade.CampaignAttributeFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request.CampaignAttributeRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response.AttributeResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@ApiManagementController
@RequiredArgsConstructor
public class CampaignAttributeController extends BaseController {

	private final CampaignAttributeFacade campaignAttributeFacade;

	@GetMapping("campaign-attributes-all")
	public Response<?> getAllCampaignAttributes(){
		return respond(AttributeResponse.fromListOfModel(campaignAttributeFacade
				.getAllCampaignAttributes()));
	}

	@GetMapping("campaign-attributes")
	public Response<?> getCampaignAttributes(@RequestParam(value="campaignStructureId") Long campaignStructureId,
			@RequestParam(value="campaignTypeId") Long campaignTypeId, @RequestParam(value="attributeTypeId") Long attributeTypeId) {
		return respond(AttributeResponse.fromListOfModel(campaignAttributeFacade
				.getCampaignAttributes(campaignTypeId, campaignStructureId, attributeTypeId)));
	}

	@GetMapping("campaign-attribute")
	public Response<?> getCampaignAttributeById(@RequestParam(value="id") Long id) {
		return respond(AttributeResponse.fromModel(campaignAttributeFacade
				.getCampaignAttributeById(id)));
	}

	@PostMapping("campaign-attribute")
	public Response<?> saveOrUpdateCampaignAttribute(@RequestBody CampaignAttributeRequest campaignAttributeRequest){
		return respond(AttributeResponse.fromModel(campaignAttributeFacade
				.saveCampaignAttribute(campaignAttributeRequest.toModel())));
	}

	@DeleteMapping("campaign-attribute")
	public Response<?> deleteCampaignAttribute(@RequestParam(value="id")Long id){

		return respond(AttributeResponse.fromModel(campaignAttributeFacade
				.deleteCampaignAttribute(id)));
	}

	@GetMapping("campaign-parent-attribute")
	public Response<?> getChildCampaignAttributeByParentId(@RequestParam(value="parentId") Long id) {
		return respond(AttributeResponse.fromListOfModel(campaignAttributeFacade
				.getChildCampaignAttributeByParentId(id)));
	}

	@GetMapping("campaign-mat-attributes")
	public Response<?> getCampaignMatAttributes() {
		return respond(AttributeResponse.fromListOfModel(campaignAttributeFacade
				.getCampaignMatAttributes()));
	}

	@GetMapping("campaign-reward-attributes")
	public Response<?> getCampaignRewardAttributes() {
		return respond(AttributeResponse.fromListOfModel(campaignAttributeFacade
				.getCampaignRewardAttributes()));
	}


}
