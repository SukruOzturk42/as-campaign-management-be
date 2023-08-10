package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.campaign.facade.DiscountCodeInformationFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request.CreateDiscountCodeInformationRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request.DiscountInformationCriteriaRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response.*;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.PageableResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@ApiManagementController
@RequiredArgsConstructor
public class DiscountCodeInformationController extends BaseController {

    private final DiscountCodeInformationFacade discountCodeInformationFacade;

    @PostMapping("cm-code/discount-code-information")
    public Response<?> saveDiscountCodeInformation(@Valid @RequestBody CreateDiscountCodeInformationRequest createDiscountCodeInformationRequest) {
        return respond(DiscountCodeInformationResponse
                .fromModel(discountCodeInformationFacade
                        .saveDiscountCodeInformation(createDiscountCodeInformationRequest
                                .toModel())));
    }

    @PostMapping("cm-code/discount-code-informations")
    public PageableResponse<?> getAllDiscountCodeInformation(@RequestBody @Valid DiscountInformationCriteriaRequest discountInformationCriteriaRequest,
                                                             @NotNull @PageableDefault(sort = {"uploadStartTime"}, direction = Sort.Direction.DESC) final Pageable pageable) {
        return respond(DiscountCodeInformationResponse.fromListOfModel(discountCodeInformationFacade.getAllDiscountCodeInformation(discountInformationCriteriaRequest.toModel(),pageable)));
    }

    @GetMapping("cm-code/pageable-discount-code-information")
    public PageableResponse<?> getPageableAllDiscountCodeInformation(@RequestParam(defaultValue = "") String codeGroupName,
                                                                     @RequestParam(defaultValue = "") String contactNumber,
                                                                     @NotNull @PageableDefault(sort = {"uploadStartTime"}, direction = Sort.Direction.DESC) final Pageable pageable) {
        return respond(discountCodeInformationFacade.getAllDiscountCodeInformation(codeGroupName,contactNumber,pageable));
    }

    @GetMapping("cm-code/discount-code-information-campaign")
    public List<UsedCodeGroupInformationResponse> getCampaignsByUsedDiscountCodeInformationId(@RequestParam Long discountCodeInformationId) {
        return UsedCodeGroupInformationResponse.fromListOfModel(discountCodeInformationFacade.getCampaignsByUsedDiscountCodeInformationId(discountCodeInformationId));
    }

    @GetMapping("cm-code/active-discount-code-information")
    public Response<?> getAllActiveDiscountCodeInformation() {
        return respond(DiscountCodeInformationResponse.fromListOfModel(discountCodeInformationFacade.getAllActiveDiscountCodeInformation()));
    }

    @GetMapping("cm-code/discount-codes")
    public Response<?> getAllDiscountCodes() {
        return respond(DiscountCodeResponse.fromListOfModel(discountCodeInformationFacade.getAllDiscountCodes()));
    }

    @GetMapping("cm-code/pageable-discount-codes")
    public PageableResponse<?> getPageableAllDiscountCodes(@RequestParam(defaultValue = "") String codeGroupName,
                                           @RequestParam(defaultValue = "") String contactNumber,
                                           @RequestParam(defaultValue = "") String code,
                                           @RequestParam(defaultValue = "") String codeStatus,
                                           @NotNull final Pageable pageable) {
        return respond(DiscountCodeResponse.fromListOfModel(discountCodeInformationFacade.getAllDiscountCodes(codeGroupName,contactNumber,code,codeStatus,pageable)));
    }

    @GetMapping("cm-code/discount-code-informations")
    public Response<?> getCodesByDiscountCodeInformationId(@RequestParam Long discountCodeInformationId) {
        var discountCodeResponse = new ExcelExportOfDiscountCodeResponse(discountCodeInformationFacade
                .getAllDiscountCodeByDiscountCodeInformation(discountCodeInformationId));
        return respond(DiscountCodeResponse.fromListOfModel(discountCodeResponse.getCodes()));
    }

    @GetMapping("cm-code/discount-code-information-template-excel")
    public ResponseEntity<InputStreamResource> getTemplateExcel() {
        var excelTemp = new DiscountCodeTemplateExcelResponse();
        return excelTemp.toExcelResponseEntity();
    }

    @GetMapping("cm-code/third-party-discount-code-information-template-excel")
    public ResponseEntity<InputStreamResource> getThirdPartyDiscountCodeTemplateExcel() {
        var excelTemp = new ThirdPartyDiscountCodeTemplateExcelResponse();
        return excelTemp.toExcelResponseEntity();
    }
}
