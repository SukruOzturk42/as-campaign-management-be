package com.anadolusigorta.campaignmanagement.infrastructure.attribute.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.attribute.facade.AttributeFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.attribute.rest.dto.response.AttributeResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import com.anadolusigorta.campaignmanagement.infrastructure.referencetype.rest.dto.AttributeReferenceTypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@ApiController
@RequiredArgsConstructor
public class AttributeRestController extends BaseController {

    private final AttributeFacade attributeFacade;

    @GetMapping("attributes")
    public Response<?> getAttributes() {
        return respond(AttributeResponse.fromListOfModel(attributeFacade.getAttributes()));
    }

    @GetMapping("attribute-reference-types")
    public Response<?> getAttributeReferenceTypeByName(@RequestParam(value = "name") String name) {
        return respond(AttributeReferenceTypeResponse.fromListOfModel(attributeFacade.getAttributeReferenceTypeByName(name)));
    }

}
