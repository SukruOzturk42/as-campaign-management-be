package com.anadolusigorta.campaignmanagement.infrastructure.company.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.company.facade.CompanyCampaignStructureFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import com.anadolusigorta.campaignmanagement.infrastructure.company.rest.dto.response.CompanyCampaignStructureResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@ApiManagementController
@RequiredArgsConstructor
public class CompanyCampaignStructureController extends BaseController {

    private final CompanyCampaignStructureFacade companyCampaignStructureFacade;

    @GetMapping("company-campaign-structure")
    public Response<?> getCompanyCampaignStructureByCompanyId(Long companyId) {
        var companyCampaignStructure = companyCampaignStructureFacade
                .getCompanyCampaignStructureByCompanyId(companyId);
        return respond(CompanyCampaignStructureResponse
                .fromModel(companyCampaignStructure));
    }

    @GetMapping("company-campaign-structures")
    public Response<?> getCompanyCampaignStructures() {
        var companyCampaignStructure = companyCampaignStructureFacade
                .getCompanyCampaignStructures();
        return respond(CompanyCampaignStructureResponse
                .fromListOfModel(companyCampaignStructure));
    }
}
