package com.anadolusigorta.campaignmanagement.infrastructure.attribute.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.attribute.facade.AttributeTypeFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.attribute.rest.dto.response.AttributeTypeResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@ApiManagementController
@RequiredArgsConstructor
public class AttributeTypeController extends BaseController {

    private final AttributeTypeFacade attributeTypeFacade;

    @GetMapping("attribute-type")
    public Response<?> getAttributeTypes() {
        return respond(AttributeTypeResponse.fromListOfModel(attributeTypeFacade.getAttributeTypes()));
    }

}
