package com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.facade.PolicySaleCampaignFacade;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.CreatePolicySaleRewardCampaign;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.rest.dto.response.PolicySaleResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.rest.dto.response.PolicySaleCustomerResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.rest.dto.response.PolicySaleRewardCampaignResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@ApiManagementController
@RequiredArgsConstructor
public class PolicySaleRewardCampaignController extends BaseController {
    private final PolicySaleCampaignFacade policySaleCampaignFacade;

    @PostMapping("cm-policy-sale/policy-sale-reward-campaign")
    public Response<?> createPolicySaleCampaign(@RequestBody CreatePolicySaleRewardCampaign createPolicySaleRewardCampaign){
        return respond(policySaleCampaignFacade.createPolicySaleRewardCampaign(createPolicySaleRewardCampaign));
    }

    @GetMapping("cm-policy-sale/policy-sale-reward-campaigns")
    public Response<?> getPolicySaleCampaigns(){
        return respond(PolicySaleRewardCampaignResponse.fromListModel(policySaleCampaignFacade.getPolicySaleRewardCampaigns()));
    }


    @GetMapping("cm-policy-sale/policy-sale-campaign-customer-list")
    public Response<?> getPolicySaleCampaignCustomerList(@RequestParam(value = "campaignId") Long  campaignId){
        return respond(PolicySaleCustomerResponse.fromListModel(policySaleCampaignFacade.getPolicySaleCampaignCustomerList(campaignId)));
    }

    @GetMapping("cm-policy-sale/policy-sale-campaign-detail")
    public Response<?> getPolicySaleCampaignDetail(@RequestParam(value = "campaignId") Long  campaignId){
        return respond(PolicySaleRewardCampaignResponse.fromModel(policySaleCampaignFacade.getPolicySaleCampaignDetail(campaignId)));
    }

    @GetMapping("cm-policy-sale/policy-sale-distribute-code-to-customer")
    public Response<?> distributeCodeToCustomer(@RequestParam(value = "campaignId") Long campaignId){
        return respond(policySaleCampaignFacade.distributeCodeToCustomer(campaignId));
    }

    @GetMapping("cm-policy-sale/sale-reward-information-list")
    public Response<?> getPolicySaleRewardCodesByGiftCodeInformationId(@RequestParam Long policySaleRewardCodeInformationId) {
        var giftCodeResponse =new PolicySaleResponse(policySaleCampaignFacade
                .getPolicySaleRewardCodesByGiftCodeInformationId(policySaleRewardCodeInformationId));
        return respond(giftCodeResponse);
    }
}
