package com.anadolusigorta.campaignmanagement.infrastructure.budgetitemtype.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.budgetitemtype.facade.BudgetITemTypesFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.budgetitemtype.rest.dto.BudgetItemTypesResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@ApiManagementController
@RequiredArgsConstructor
public class BudgetItemTypesController  extends BaseController {

    private final BudgetITemTypesFacade budgetITemTypesFacade;
    @GetMapping("campaign-budget-items-type")
    public Response<?> getCampaignBudgetsByCampaignId() {
        return respond(BudgetItemTypesResponse.fromModel(budgetITemTypesFacade.getAllBudgetTypes()));
    }

}
