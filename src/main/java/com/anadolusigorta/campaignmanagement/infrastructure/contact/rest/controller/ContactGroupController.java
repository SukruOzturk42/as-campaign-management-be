/* dks20165 created on 13.09.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.controller */

package com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.controller;
import com.anadolusigorta.campaignmanagement.domain.contact.facade.ContactGroupFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.PageableResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.request.ContactGroupCriteriaRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.request.ContactGroupRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.response.ContactGroupResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.response.ContactGroupTemplateExcelResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@ApiManagementController
@RequiredArgsConstructor
public class ContactGroupController extends BaseController {

	private final ContactGroupFacade contactGroupFacade;


	@GetMapping("cm-contact/contact-groups")
	public Response<?> getAllContactGroup() {

		return respond(ContactGroupResponse
				.fromListModel(contactGroupFacade.getAllContactGroup()));

	}

	@PostMapping(value = "cm-contact/pageable-contact-groups")
	public PageableResponse<?> getAllContactGroupsCriteria(@RequestBody @Valid ContactGroupCriteriaRequest contactGroupCriteriaRequest,
													  @NotNull final Pageable pageable){
		return respond(ContactGroupResponse.fromListOfModel(contactGroupFacade
				.getAllContactGroupsCriteria(contactGroupCriteriaRequest.toModel(),pageable)));
	}

	@GetMapping("cm-contact/contact-group")
	public Response<?> getAllContactGroup(@RequestParam(value = "contactGroupId") Long contactGroupId) {

		return respond(ContactGroupResponse.fromModel(contactGroupFacade.getContactGroup(contactGroupId)));

	}

	@PutMapping("cm-contact/contact-group")
	public Response<?> saveContactGroup(@RequestBody @Valid ContactGroupRequest contactGroupRequest) {

		return respond(ContactGroupResponse
				.fromModel(contactGroupFacade
						.saveContactGroup(contactGroupRequest.toModel())));

	}

	@PostMapping("cm-contact/contact-group")
	public Response<?> saveContactGroup(@RequestBody @Valid ContactGroupRequest contactGroupRequest,
			@RequestParam(value = "contactGroupId") Long contactGroupId) {

		return respond(ContactGroupResponse
				.fromModel(contactGroupFacade
						.saveContactGroup(contactGroupRequest.toModel(),contactGroupId)));

	}

	@DeleteMapping("cm-contact/contact-group")
	public Response<?> deleteContactGroup(@RequestParam(value = "contactGroupId") Long contactGroupId) {

		return respond(ContactGroupResponse.fromModel(contactGroupFacade.deleteContactGroup(contactGroupId)));

	}

	@GetMapping("cm-contact/contact-group-excel-template")
	public ResponseEntity<InputStreamResource> getTemplateExcel() {
		var excelTemp = new ContactGroupTemplateExcelResponse();
		return excelTemp.toExcelResponseEntity();
	}

	@GetMapping("cm-contact/contact-group-export")
	public Response<?> getContactsExport(@RequestParam(value = "contactGroupId") Long contactGroupId) {
		return respond(contactGroupFacade.getContactsByContactGroupId(contactGroupId));
	}



}
