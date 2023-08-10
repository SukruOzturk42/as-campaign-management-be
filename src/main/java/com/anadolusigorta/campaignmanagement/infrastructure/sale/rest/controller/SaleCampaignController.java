/* odeon_sukruo created on 26.04.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.product.rest.controller */

package com.anadolusigorta.campaignmanagement.infrastructure.sale.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.customercampaign.facade.CustomerCampaignFacade;
import com.anadolusigorta.campaignmanagement.domain.sale.facade.SaleCampaignFacade;
import com.anadolusigorta.campaignmanagement.domain.sale.model.SaleInformationSummary;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request.CampaignCriteriaRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response.CampaignInformationResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.PageableResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.rest.dto.request.SaleCampaignInformationCriteriaRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.rest.dto.response.ExcelExportCampaignSaleResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.rest.dto.response.SaleCampaignInformationResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.rest.dto.response.SaleReportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@ApiManagementController
@RequiredArgsConstructor
public class SaleCampaignController extends BaseController {

	private final SaleCampaignFacade saleCampaignFacade;

	private final CustomerCampaignFacade customerCampaignFacade;



	@PostMapping("cm-dashboard/pageable-sale-information")
	public PageableResponse<?> getPageableAllSaleCampaignInformation(@RequestBody @Valid SaleCampaignInformationCriteriaRequest saleCampaignInformationCriteriaRequest,
															 @NotNull final Pageable pageable) {
		return respond(SaleReportResponse
				.fromListOfModel(saleCampaignFacade
						.getSaleInformation(saleCampaignInformationCriteriaRequest.toModel(),pageable)));
	}

	@GetMapping("cm-dashboard/sale-campaign-summary")
	public Response<SaleInformationSummary> getSaleCampaignSummary(
			@RequestParam(value = "campaignId") Long campaignId) {
		return respond(saleCampaignFacade.getSaleCampaignSummary(campaignId));
	}

	@GetMapping("sale-campaigns")
	public Response<?> getSaleCampaignOperation() {
		return respond(
				SaleCampaignInformationResponse.fromListOfModel(saleCampaignFacade.findSaleCampaignInformation()));
	}



	@GetMapping("cm-dashboard/trigger-sale-reward-send-operation")
	public Response<?> triggerSaleRewardSendOperation(@RequestParam(value = "campaignId") Long campaignId) {
		return respond(saleCampaignFacade.triggerSaleRewardSendOperation(campaignId));
	}

	@GetMapping("cm-dashboard/start-sale-reward-send-operation")
	public Response<?> startSaleRewardSendOperation(@RequestParam(value = "campaignId") Long campaignId) {
		return respond(saleCampaignFacade.startSaleRewardSendOperation(campaignId));
	}

	@GetMapping("cm-dashboard/stop-sale-reward-send-operation")
	public Response<?> stopSaleRewardSendOperation(@RequestParam(value = "campaignId") Long campaignId) {
		return respond(saleCampaignFacade.stopSaleRewardSendOperation(campaignId));
	}

	@GetMapping("cm-dashboard/stop-sale-campaigns-reward-send-operation")
	public Response<?> stopSaleCampaignsRewardSendOperation() {
		return respond(saleCampaignFacade.stopSaleCampaignRewardSendOperation());

	}

	@GetMapping("cm-dashboard/start-sale-campaigns-reward-send-operation")
	public Response<?> startSaleCampaignsRewardSendOperation() {
		return respond(saleCampaignFacade.startSaleCampaignRewardSendOperation());
	}

	@PostMapping("cm-dashboard/campaign-sales")
	public Response<?> exportAllSaleCampaignInformation(@RequestBody @Valid SaleCampaignInformationCriteriaRequest saleCampaignInformationCriteriaRequest) {

		return respond(SaleReportResponse
				.fromListOfModel(saleCampaignFacade
						.getSaleInformation(saleCampaignInformationCriteriaRequest.toModel())));

	}

	@PostMapping("cm-dashboard/pageable-customer-campaigns")
	public PageableResponse<?> getPageableCampaigns(@RequestBody @Valid CampaignCriteriaRequest saleCampaignInformationCriteriaRequest,
													@NotNull final Pageable pageable) {
		return  respond(CampaignInformationResponse
				.fromListOfModel(customerCampaignFacade
						.getContactCampaignsInformationPageable(saleCampaignInformationCriteriaRequest.toModel(),pageable)));
	}


}
