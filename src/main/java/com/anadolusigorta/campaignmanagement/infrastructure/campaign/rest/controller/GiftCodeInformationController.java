package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.campaign.facade.GiftCodeInformationFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request.CreateGiftCodeInformationRequest;
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
public class GiftCodeInformationController extends BaseController {

    private final GiftCodeInformationFacade giftCodeInformationFacade;

    @GetMapping("cm-code/gift-code-information")
    public Response<?> getAllGiftCodeInformation() {
        return respond(GiftCodeInformationResponse
                .fromListModel(giftCodeInformationFacade.getAllGiftCodeInformation()));
    }

    @PostMapping("cm-code/pageable-gift-code-information")
    public PageableResponse<?> getAllPageableGiftCodeInformation(@NotNull @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) final Pageable pageable) {
        return respond(GiftCodeInformationResponse.fromListOfModel(giftCodeInformationFacade.getAllPageableGiftCodeInformation(pageable)));
    }

    @PostMapping("cm-code/gift-code-information")
    public Response<?> saveGiftCodeInformation(@Valid @RequestBody CreateGiftCodeInformationRequest createGiftCodeInformationRequest) {
        return respond(GiftCodeInformationResponse.fromModel(giftCodeInformationFacade.saveGiftCodeInformation(createGiftCodeInformationRequest.toModel())));
    }

    @GetMapping("cm-code/gift-code-information-campaign")
    public List<UsedCodeGroupInformationResponse> getCampaignsByUsedGiftCodeInformationId(@RequestParam Long giftCodeInformationId) {
        return UsedCodeGroupInformationResponse.fromListOfModel(giftCodeInformationFacade.getCampaignsByUsedGiftCodeInformationId(giftCodeInformationId));
    }

    @GetMapping("cm-code/gift-codes")
    public Response<?> getAllGiftCodes() {
        return respond(GiftCodeResponse.fromListOfModel(giftCodeInformationFacade.getAllGiftCodes()));
    }

    @GetMapping("cm-code/reward-gift-codes")
    public Response<?> getAllGiftCodes(@RequestParam Long rewardGiftId) {
        return respond(GiftCodeResponse.fromListOfModel(giftCodeInformationFacade.getRewardAGiftCodes(rewardGiftId)));
    }


    @GetMapping("cm-code/pageable-gift-codes")
    public PageableResponse<?> getPageableAllGiftCodes(@RequestParam(defaultValue = "") String codeGroupName,
                                                       @RequestParam(defaultValue = "") String contactNumber,
                                                       @RequestParam(defaultValue = "") String code,
                                                       @RequestParam(defaultValue = "") String codeStatus,
                                                       @NotNull final Pageable pageable) {

        return respond(GiftCodeResponse.fromListOfModel(giftCodeInformationFacade.getAllGiftCodes(codeGroupName,contactNumber,code,codeStatus,pageable)));
    }

    @GetMapping("cm-code/gift-code-information-template-excel")
    public ResponseEntity<InputStreamResource> getTemplateExcel() {
        var excelTemp = new GiftCodeTemplateExcelResponse();
        return excelTemp.toExcelResponseEntity();
    }

    @GetMapping("cm-code/gift-code-informations")
    public Response<?> getCodesByGiftCodeInformationId(@RequestParam Long giftCodeInformationId) {
        var giftCodeResponse = new ExcelExportOfGiftCodeResponse(giftCodeInformationFacade
                .getAllGiftCodeByGiftCodeInformationId(giftCodeInformationId));
        return respond(GiftCodeResponse
                .fromListOfModel(giftCodeResponse.getCodes()));
    }
}
