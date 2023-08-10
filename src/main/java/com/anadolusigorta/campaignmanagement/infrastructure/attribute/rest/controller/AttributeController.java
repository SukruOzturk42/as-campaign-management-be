package com.anadolusigorta.campaignmanagement.infrastructure.attribute.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.attribute.facade.AttributeFacade;
import com.anadolusigorta.campaignmanagement.domain.operator.AttributeOperator;
import com.anadolusigorta.campaignmanagement.infrastructure.attribute.rest.dto.AttributeOperatorResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.attribute.rest.dto.request.CreateAttributeRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.attribute.rest.dto.response.AttributeResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@ApiManagementController
@RequiredArgsConstructor
public class AttributeController extends BaseController {

    private final AttributeFacade attributeFacade;

    @GetMapping("attributes")
    public Response<?> getAttributes() {
        return respond(AttributeResponse.fromListOfModel(attributeFacade.getAttributes()));
    }

    @GetMapping("attribute")
    public Response<?> getAttributeById(@RequestParam(value="id") Long id) {
        return respond(AttributeResponse.fromModel(attributeFacade
                        .getAttributeById(id)));
    }

    @PostMapping("attribute")
    public Response<?> saveOrUpdateAttribute(@RequestBody CreateAttributeRequest createAttributeRequest,@RequestParam(value="campaignTypeId") Long campaignTypeId){
        return respond(AttributeResponse.fromModel(attributeFacade.saveAttribute(createAttributeRequest.toModel(),campaignTypeId)));
    }

    @DeleteMapping("attribute")
    public Response<?> deleteAttribute(@RequestParam(value = "id") Long id){
        return respond(AttributeResponse.fromModel(attributeFacade.deleteAttribute(id)));
    }

    @GetMapping("attribute-operator")
    public Response<?> getAttributeOperators(@RequestParam(value = "campaign-attributeId") Long id) {
        return respond(AttributeOperatorResponse.fromListOfModel(attributeFacade.getAttributeOperator(id)));
    }

    @GetMapping("attribute-campaign-type")
    public Response<?> getAttributeByCampaignTypeId(@RequestParam(value = "campaignTypeId") Long campaignTypeId,
            @RequestParam(value = "structureName") String structureName) {
        return respond(AttributeResponse.fromListOfModel(attributeFacade
                .getAttributeByCampaignTypeIdAndStructureName(campaignTypeId,structureName)));
    }

    @GetMapping("attributes-campaign-type")
    public Response<?> getAttributeByCampaignTypeId(@RequestParam(value = "campaignTypeId") Long campaignTypeId) {
        return respond(AttributeResponse.fromListOfModel(attributeFacade
                .getAllAttributeByCampaignTypeId(campaignTypeId)));
    }

    @GetMapping("attribute-operator-id")
    public Response<?> getAttributeOperatorByAttributeId(@RequestParam(value = "attributeId") Long attributeId) {
        return respond(AttributeOperatorResponse.fromListOfModel(attributeFacade.getAttributeOperatorByAttributeId(attributeId)));
    }

    @DeleteMapping("delete-attribute-operator")
    public Response<?> deleteAttributeOperator(@RequestParam(value = "id") Long id) {
        return respond(AttributeOperatorResponse.fromModel(attributeFacade.deleteOperatorByAttributeId(id)));
    }

    @PostMapping("add-attribute-operator")
    public Response<?> addAttributeOperator(@RequestParam(value = "attributeId") Long attributeId,
                                            @RequestParam(value = "operator") String operator) {
        return respond(AttributeOperatorResponse.fromModel(attributeFacade.addAttributeOperator(attributeId,operator)));
    }

    @PostMapping("add-all-attribute-operators")
    public Response<?> addAllAttributeOperators(@RequestParam(value = "attributeId") Long attributeId,
                                            @RequestParam(value="operators") List<String> operators) {
        List<AttributeOperator> attributeOperatorList = new ArrayList<>();
        operators.forEach(item -> {
            attributeOperatorList.add(attributeFacade.addAttributeOperator(attributeId,item));
        });
        return respond(AttributeOperatorResponse.fromListOfModel(attributeOperatorList));
    }

    @PutMapping("update-attribute-operator")
    public Response<?> updateAttributeOperator(@RequestParam(value = "attributeId") Long attributeId,
                                            @RequestParam(value = "operator") String operator,@RequestParam(value = "id") Long id) {
        return respond(AttributeOperatorResponse.fromModel(attributeFacade.updateAttributeOperator(attributeId,operator,id)));
    }


}
