package com.anadolusigorta.campaignmanagement.infrastructure.campaignbudget.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.campaignbudget.facade.CampaignBudgetFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignbudget.rest.dto.request.CreateCampaignBudgetRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignbudget.rest.dto.response.CampaignBudgetListResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignbudget.rest.dto.response.CampaignBudgetResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@ApiManagementController
@RequiredArgsConstructor
public class CampaignBudgetController extends BaseController {

    private final CampaignBudgetFacade campaignBudgetFacade;

    @PostMapping("campaign-budget")
    public Response<?> saveBudget(@Valid @RequestBody CreateCampaignBudgetRequest createCampaignBudgetRequest) {
        return respond(CampaignBudgetResponse
                .fromModel(campaignBudgetFacade.saveCampaignBudget(createCampaignBudgetRequest.toModel())));
    }

    @GetMapping("campaign-budgets/{campaignId}")
    public Response<?> getCampaignBudgetsByCampaignId(@PathVariable Long campaignId) {
        return respond(CampaignBudgetListResponse.fromModel(campaignBudgetFacade.getAllCampaignBudgets(campaignId)));
    }

    @DeleteMapping("campaign-budget/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCampaignBudget(@PathVariable("id") Long id){
        campaignBudgetFacade.deleteCampaignBudget(id);
    }



}
