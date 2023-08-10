package com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.facade.CodeFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response.DiscountCodeResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response.GiftCodeResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.rest.dto.request.CodeCriteriaRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.PageableResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;

@ApiManagementController
@RequiredArgsConstructor
public class CodeController extends BaseController {

    private final CodeFacade codeFacade;

    @PostMapping(value = "cm-code/pageable-discount-code")
    public PageableResponse<?> getDiscountCodesByCriteria(@RequestBody CodeCriteriaRequest codeCriteriaRequest,
                                                          @NotNull final Pageable pageable){
       return respond(DiscountCodeResponse.fromListOfModel(codeFacade
               .getDiscountCodesByCriteria(codeCriteriaRequest.toModel(),pageable)));
    }

    @PostMapping(value = "cm-code/pageable-gift-code")
    public PageableResponse<?> getGiftCodesByCriteria(@RequestBody CodeCriteriaRequest codeCriteriaRequest,
                                                          @NotNull final Pageable pageable){
        return respond(GiftCodeResponse.fromListOfModel(codeFacade
                .getGiftCodesByCriteria(codeCriteriaRequest.toModel(),pageable)));
    }

}
