package com.anadolusigorta.campaignmanagement.infrastructure.businessmessage.rest.controller;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.infrastructure.businessmessage.jpa.entity.BusinessMessageEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.businessmessage.jpa.repository.BusinessMessageJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.businessmessage.rest.dto.request.CreateBusinessMessageRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.businessmessage.rest.dto.response.BusinessMessageResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.businessmessage.rest.dto.response.ExcelExportOfBusinessMessageResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.ExceptionConstants;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@ApiManagementController
@RequiredArgsConstructor
public class BusinessMessageController extends BaseController {

    private final BusinessMessageJpaRepository businessMessageJpaRepository;

    @PostMapping("business-message")
    public Response<?> saveBusinessMessage(@Valid @RequestBody CreateBusinessMessageRequest createBusinessMessageRequest) {
        var optionalBusinessMessageEntity =businessMessageJpaRepository.findByKey(createBusinessMessageRequest.getKey());
        if(optionalBusinessMessageEntity.isEmpty()){
            return respond(businessMessageJpaRepository
                    .save(BusinessMessageEntity
                            .fromModel(createBusinessMessageRequest)));

        } else{ throw new CampaignManagementException("business.message.key.already.defined");}
    }

    @GetMapping("business-message")
    public Response<?> getAllBusinessMessages(){
        return respond(BusinessMessageResponse
                .fromListOfModel(businessMessageJpaRepository
                        .findAll()));
    }

    @GetMapping("business-message/{id}")
    public Response<?> getBusinessMessageById(@PathVariable("id") Long id) {
        return respond(BusinessMessageResponse
                .fromModel(businessMessageJpaRepository
                        .findById(id).orElseThrow(()->new CampaignManagementException(ExceptionConstants.BUSINESS_MESSAGE_NOT_FOUND,"id",Long.toString(id)))));
    }

    @DeleteMapping("business-message")
    public Response<?> deleteBusinessMessage(@RequestParam(value = "id") Long id) {
        var businessMessageEntity = businessMessageJpaRepository.findById(id)
                .orElseThrow(()->new CampaignManagementException(ExceptionConstants.BUSINESS_MESSAGE_NOT_FOUND,"id",Long.toString(id)));
        var businessMessageResponse=BusinessMessageResponse.fromModel(businessMessageEntity);
        businessMessageJpaRepository.delete(businessMessageEntity);
        return respond(businessMessageResponse);
    }

    @PutMapping("business-message")
    public Response<?> updateBusinessMessage(@RequestBody @Valid CreateBusinessMessageRequest createBusinessMessageRequest) {
        var businessMessageEntity=businessMessageJpaRepository.findById(createBusinessMessageRequest.getId())
                .orElseThrow(()->new CampaignManagementException(ExceptionConstants.BUSINESS_MESSAGE_NOT_FOUND,"id",Long.toString(createBusinessMessageRequest.getId())));
        businessMessageEntity.setNote(createBusinessMessageRequest.getNote());
        businessMessageEntity.setDescription(createBusinessMessageRequest.getDescription());
        businessMessageEntity.setCode(createBusinessMessageRequest.getCode());
        businessMessageEntity.setKey(createBusinessMessageRequest.getKey());
        return respond(BusinessMessageResponse
                .fromModel(businessMessageJpaRepository
                        .save(businessMessageEntity)));
    }

    @GetMapping("business-message-excel-export")
    public ResponseEntity<InputStreamResource> exportBusinessMessage() {
        var excelTemp = ExcelExportOfBusinessMessageResponse.builder()
                .messageList(BusinessMessageResponse
                        .fromListOfModel(businessMessageJpaRepository
                                .findAll()))
                .build();

        return excelTemp.toExcelResponseEntity();
    }

}
