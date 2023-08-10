/* odeon_sukruo created on 26.04.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.controller */

package com.anadolusigorta.campaignmanagement.infrastructure.company.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.company.facade.CompanyFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.company.rest.dto.response.CompanyResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@ApiManagementController
@RequiredArgsConstructor
public class CompanyController extends BaseController {

	private final CompanyFacade companyFacade;

	@GetMapping("company")
	public Response<?> getCompanies() {
		var companies = companyFacade.getCompanies();
		return respond(CompanyResponse.fromListOfModel(companies));
	}
}
