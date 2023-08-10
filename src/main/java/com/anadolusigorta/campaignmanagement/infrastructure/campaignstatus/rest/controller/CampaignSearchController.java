package com.anadolusigorta.campaignmanagement.infrastructure.campaignstatus.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.campaignstatus.facade.CampaignStatusFacade;
import com.anadolusigorta.campaignmanagement.domain.customercampaign.facade.CustomerCampaignFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request.ApprovalCampaignCriteriaRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request.CampaignCriteriaRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response.CampaignInformationResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignstatus.jpa.adapter.CampaignSearchRepositoryJpaAdapter;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.PageableResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import com.anadolusigorta.campaignmanagement.infrastructure.referencetype.rest.dto.AttributeReferenceTypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.stream.Collectors;

@ApiManagementController
@RequiredArgsConstructor
public class CampaignSearchController extends BaseController {
    private final CampaignStatusFacade campaignStatusFacade;
    private final CampaignSearchRepositoryJpaAdapter campaignSearchRepositoryJpaAdapter;
    private final CustomerCampaignFacade campaignFacade;

    @GetMapping("campaign-search-status")
    public Response<?> getCampaignSearchStatusEnumList() {
        var campaignSearchStatusList = campaignStatusFacade.getCampaignSearchStatus();
        return respond(campaignSearchStatusList.stream()
                .map(item -> AttributeReferenceTypeResponse.builder()
                        .name(item.getValue()).description(item.getValue()).build())
                .collect(Collectors.toList()));
    }

    @GetMapping("campaign-search-approval")
    public Response<?> getCampaignSearchApprovalEnumList() {
        var campaignSearchApprovalList = campaignStatusFacade.getCampaignSearchApproval();
        return respond(campaignSearchApprovalList.stream()
                .map(item -> AttributeReferenceTypeResponse.builder().name(item.getValue()).description(item.getValue()).build())
                .collect(Collectors.toList()));
    }

    @GetMapping("cm-campaign/campaign-search")
    public Response<?> getCampaignsByCampaignSearch(@RequestParam(value = "campaignSearchStatusEnum", required = false) String campaignSearchStatusEnum,
                                                    @RequestParam(value = "campaignTypeId", required = false) Long campaignTypeId) {
        return respond(CampaignInformationResponse.fromListOfModel(campaignFacade.getContactCampaignsInformation(campaignSearchStatusEnum,campaignTypeId)));
    }

    @GetMapping("cm-campaign/approval-campaign-search")
    public Response<?> getApprovalCampaignsByCampaignSearch(@RequestParam(value = "approvalCampaignSearchStatusEnum", required = false) String approvalCampaignSearchStatusEnum,
                                                    @RequestParam(value = "campaignTypeId", required = false) Long campaignTypeId) {
        return respond(CampaignInformationResponse
                .fromListOfModel(campaignStatusFacade.getApprovalCampaignsInformation(approvalCampaignSearchStatusEnum,campaignTypeId)));
    }

    @PostMapping("cm-campaign/pageable-approval-campaign-search")
    public PageableResponse<?> getPageableApprovalCampaignsByCampaignSearch(@RequestBody @Valid ApprovalCampaignCriteriaRequest approvalCampaignCriteriaRequest,
                                                                            @NotNull final
                                                                            @SortDefault(sort = "createDate", direction = Sort.Direction.DESC)
                                                                            Pageable pageable) {
        return respond(CampaignInformationResponse
                .fromListOfModel(campaignStatusFacade.getPageableApprovalCampaignsInformation(approvalCampaignCriteriaRequest.toModel(),pageable)));
    }

    @GetMapping("cm-campaign/get-reference-campaign")
    public Response<?> getDiscountCodeDefinedParticipantCampaigns() {
        return respond(CampaignInformationResponse
                .fromListOfModel(campaignFacade.getDiscountCodeDefinedParticipantCampaigns()));
    }

    @PostMapping("cm-campaign/campaign-search-export")
    public Response<?> exportCustomerCampaign(@RequestBody @Valid CampaignCriteriaRequest campaignCriteriaRequest) {
        return respond(CampaignInformationResponse
                        .fromListOfModel(campaignFacade
                                .getContactCampaignsInformation(campaignCriteriaRequest.toModel())));
    }

    @GetMapping("cm-campaign/approval-campaign-search-export")
    public Response<?> exportApprovalCampaign(@RequestParam(value = "approvalCampaignSearchStatusEnum", required = false) String approvalCampaignSearchStatusEnum,
                                              @RequestParam(value = "campaignTypeId", required = false) Long campaignTypeId) {
        return respond(CampaignInformationResponse.fromListOfModel(campaignStatusFacade
                .getApprovalCampaignsInformation(approvalCampaignSearchStatusEnum,campaignTypeId)));

    }

}
