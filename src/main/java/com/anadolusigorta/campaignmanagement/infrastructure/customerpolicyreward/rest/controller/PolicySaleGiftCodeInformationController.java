package com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.facade.PolicySaleGiftCodeFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.rest.dto.request.CreatePolicySaleGiftCodeInformationRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.rest.dto.response.PolicySaleCsvExportResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.rest.dto.response.PolicySaleGiftCodeInformationResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.rest.dto.CreateRewardCompanyInformation;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.rest.dto.CreateRewardTicketRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.rest.dto.RewardCompanyInformationResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.rest.dto.RewardTicketTypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@ApiManagementController
@RequiredArgsConstructor
public class PolicySaleGiftCodeInformationController extends BaseController {

	private final PolicySaleGiftCodeFacade policySaleGiftCodeFacade;

	@GetMapping("cm-policy-sale/policy-sale-gift-code-information")
	public List<PolicySaleGiftCodeInformationResponse> getAllPolicySaleGiftCodeInformation() {
		return PolicySaleGiftCodeInformationResponse
				.fromListModel(policySaleGiftCodeFacade.getAllPolicySaleGiftCodeInformation());
	}

	@PostMapping("cm-policy-sale/policy-sale-gift-code-information")
	public PolicySaleGiftCodeInformationResponse savePolicySaleGiftCodeInformation(
			@RequestBody CreatePolicySaleGiftCodeInformationRequest createPolicySaleGiftCodeInformationRequest) {
		return PolicySaleGiftCodeInformationResponse.fromModel(policySaleGiftCodeFacade
				.savePolicySaleGiftCodeInformation(createPolicySaleGiftCodeInformationRequest.toModel()));
	}

	@GetMapping("cm-policy-sale/policy-sale-reward-company-information")
	public List<RewardCompanyInformationResponse> getRewardCompanyInformation() {
		return RewardCompanyInformationResponse.fromListOfModel(policySaleGiftCodeFacade.findAllRewardCompanyInformation());
	}

	@PostMapping("cm-policy-sale/policy-sale-reward-company-information")
	public RewardCompanyInformationResponse saveRewardCompanyInformation(@RequestBody CreateRewardCompanyInformation createRewardCompanyInformation) {
		return RewardCompanyInformationResponse.fromModel(policySaleGiftCodeFacade.saveRewardCompanyInformation(createRewardCompanyInformation.toModel()));
	}

	@GetMapping("cm-policy-sale/policy-sale-reward-gift-ticket")
	public List<RewardTicketTypeResponse> getRewardTickets() {
		return RewardTicketTypeResponse.fromListOfModel(policySaleGiftCodeFacade.findAllRewardGiftTicketType());
	}

	@PostMapping("cm-policy-sale/policy-sale-reward-gift-ticket")
	public RewardTicketTypeResponse saveRewardGiftTicket(@RequestBody CreateRewardTicketRequest createRewardTicketRequest) {
		return RewardTicketTypeResponse.fromModel(policySaleGiftCodeFacade
				.saveRewardGiftTicketType(createRewardTicketRequest.toModel()));
	}

	@GetMapping("cm-policy-sale/sale-code-information-list")
	public Response<?> getPolicySaleCodesByGiftCodeInformationId(@RequestParam Long policySaleGiftCodeInformationId) {
		var giftCodeResponse =new PolicySaleCsvExportResponse(policySaleGiftCodeFacade
				.getPolicySaleCodesByGiftCodeInformationId(policySaleGiftCodeInformationId));
		return respond(giftCodeResponse);
	}

}
