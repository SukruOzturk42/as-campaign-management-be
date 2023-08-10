package com.anadolusigorta.campaignmanagement.infrastructure.recordtracking.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.recordtracking.facade.SaleProcessRecordTrackingFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request.CampaignCriteriaRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response.CampaignInformationResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.PageableResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.recordtracking.rest.dto.SaleProcessCriteriaRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.recordtracking.rest.dto.SaleProcessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@ApiManagementController
@RequiredArgsConstructor
public class SaleProcessRecordTrackingController extends BaseController {

    private final SaleProcessRecordTrackingFacade saleProcessRecordTrackingFacade;

    @PostMapping("cm-record/pagable-find-request-processes")
    public PageableResponse<?> getPageableSaleProcesses(@RequestBody @Valid SaleProcessCriteriaRequest saleProcessCriteriaRequest,
                                                    @NotNull final Pageable pageable) {
        return  respond(SaleProcessResponse
                .fromPageOfModel(saleProcessRecordTrackingFacade
                        .getFindRequestsPageable(saleProcessCriteriaRequest.toModel(),pageable)));

    }

    @PostMapping("cm-record/pagable-check-request-processes")
    public PageableResponse<?> getPageableCheckProcesses(@RequestBody @Valid SaleProcessCriteriaRequest saleProcessCriteriaRequest,
                                                         @NotNull final Pageable pageable) {
        return  respond(SaleProcessResponse
                .fromPageOfModel(saleProcessRecordTrackingFacade
                        .getCheckPageable(saleProcessCriteriaRequest.toModel(),pageable)));

    }

    @PostMapping("cm-record/pagable-notify-request-processes")
    public PageableResponse<?> getPageableNotifyProcesses(@RequestBody @Valid SaleProcessCriteriaRequest saleProcessCriteriaRequest,
                                                    @NotNull final Pageable pageable) {
        return  respond(SaleProcessResponse
                .fromPageOfModel(saleProcessRecordTrackingFacade
                        .getNotifyPageable(saleProcessCriteriaRequest.toModel(),pageable)));

    }
}
