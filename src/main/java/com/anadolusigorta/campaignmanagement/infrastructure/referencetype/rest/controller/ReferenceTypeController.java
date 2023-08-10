/* odeon_sukruo created on 29.04.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.controller */

package com.anadolusigorta.campaignmanagement.infrastructure.referencetype.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.referencetype.facade.ReferenceTypeFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import com.anadolusigorta.campaignmanagement.infrastructure.referencetype.rest.dto.AttributeReferenceTypeContainsAttributeIdRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.referencetype.rest.dto.AttributeReferenceTypeResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.referencetype.rest.dto.CreateUpdateReferenceTypeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@ApiManagementController
@RequiredArgsConstructor
public class ReferenceTypeController extends BaseController {

	private final ReferenceTypeFacade referenceTypeFacade;

	@GetMapping("campaign-attribute-reference-type")
	public Response<?> getCampaignAttributeById(@RequestParam(value = "campaignAttributeId") Long attributeId) {
		return respond(AttributeReferenceTypeResponse
				.fromListOfModel(referenceTypeFacade.getReferenceTypeByCampaignAttributeId(attributeId)));
	}

	@GetMapping("attribute-reference-type")
	public Response<?> getAttributeById(@RequestParam(value = "attributeId") Long attributeId) {
		return respond(AttributeReferenceTypeResponse
				.fromListOfModel(referenceTypeFacade.getReferenceTypeByAttributeId(attributeId)));
	}

	@DeleteMapping("attribute-reference-type")
	public void deleteReferenceType(@RequestParam(value = "id") Long id) {
		referenceTypeFacade.deleteReferenceType(id);
	}

	@GetMapping("campaign-type-attribute-reference-type")
	public Response<?> getAttributesByName(@RequestParam(value = "attributeName") String attributeName,
			@RequestParam(value = "campaignTypeId") Long campaignTypeId) {
		return respond(AttributeReferenceTypeResponse.fromListOfModel(
				referenceTypeFacade.getReferenceTypeByAttributeNameAndCampaignType(attributeName, campaignTypeId)));
	}

	@GetMapping("campaign-type-name-attribute-reference-type")
	public Response<?> getAttributesByNameAndCampaignTypeName(@RequestParam(value = "attributeName") String attributeName,
										   @RequestParam(value = "campaignTypeName") String campaignTypeName) {
		return respond(AttributeReferenceTypeResponse.fromListOfModel(
				referenceTypeFacade.getReferenceTypeByAttributeNameAndCampaignTypeName(attributeName, campaignTypeName)));
	}

	@PostMapping("attribute-reference-type")
	public Response<?> createOrUpdateReferenceType(
			@RequestBody CreateUpdateReferenceTypeRequest createUpdateReferenceTypeRequest) {
		return respond(AttributeReferenceTypeResponse.fromModel(
				referenceTypeFacade.createOrUpdateAttributeReferenceType(createUpdateReferenceTypeRequest.toModel())));
	}

	@PostMapping("attribute-reference-type-contains-attribute")
	public Response<?> createOrUpdateReferenceTypeContainsAttributeId(
			@RequestBody AttributeReferenceTypeContainsAttributeIdRequest attributeReferenceTypeContainsAttributeIdRequest,
			@RequestParam(value = "attributeId") Long attributeId) {
		attributeReferenceTypeContainsAttributeIdRequest.setAttributeId(attributeId);
		return respond(AttributeReferenceTypeResponse.fromModel(
				referenceTypeFacade.createOrUpdateAttributeReferenceType(attributeReferenceTypeContainsAttributeIdRequest.toModel())));
	}

}
