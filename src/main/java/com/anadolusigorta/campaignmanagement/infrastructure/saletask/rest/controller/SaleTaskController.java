/* odeon_sukruo created on 26.04.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.product.rest.controller */

package com.anadolusigorta.campaignmanagement.infrastructure.saletask.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.saletask.facade.SaleTaskFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@ApiManagementController
@RequiredArgsConstructor
public class SaleTaskController extends BaseController {

	private final SaleTaskFacade saleTaskFacade;



	@GetMapping("agency-sale-task")
	public Response<?> getSaleTask() {
		return respond(saleTaskFacade.getCurrentUserSaleTasks());
	}


}
