package com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.contact.facade.ContactFacade;
import com.anadolusigorta.campaignmanagement.domain.contact.facade.ContactInformationFacade;
import com.anadolusigorta.campaignmanagement.domain.contact.model.ContactInformationSearch;
import com.anadolusigorta.campaignmanagement.domain.sale.facade.SaleCampaignFacade;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.facade.CampaignTransactionalSaleProcess;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.request.*;
import com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.response.CheckCodeStatusResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.response.ContactCampaignsResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.response.NotifySaleCampaignResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.response.RemoveSaleCampaignResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@ApiController
@RequiredArgsConstructor
public class SaleCampaignProcessController extends BaseController {

    private final CampaignTransactionalSaleProcess campaignTransactionalSaleProcess;
    private final ContactFacade contactFacade;
    private final SaleCampaignFacade saleCampaignFacade;

    private final ContactInformationFacade contactInformationFacade;



    @PostMapping("contact-campaigns")
    public ContactCampaignsResponse getContactCampaigns(@RequestBody @Valid CustomerCampaignsRequest customerCampaignsRequest) {

        var campaigns=campaignTransactionalSaleProcess
                .handleFindCampaignsProcess(customerCampaignsRequest.toModel());

        return ContactCampaignsResponse.fromModel(customerCampaignsRequest,campaigns);

    }

    @PostMapping("contact-information")
    public Response<?> getContactCampaigns(@RequestBody @Valid ContactInformationSearch contactInformationSearch) {

     return respond(contactInformationFacade.getContactInformation(contactInformationSearch));

    }




    @PostMapping(value = "check-code-status")
    public CheckCodeStatusResponse checkCodeStatus(@RequestBody CheckCodeStatusRequest checkCodeStatusRequest) {

        var checkSaleStatus=campaignTransactionalSaleProcess
                .handleCheckProcess(checkCodeStatusRequest.toModel());

        return CheckCodeStatusResponse.fromModel(checkSaleStatus);
    }

    @PostMapping(value = "check-campaign-status")
    public CheckCodeStatusResponse checkCampaignStatus(@RequestBody CheckCampaignStatusRequest checkCampaignStatusRequest) {

        var checkSaleStatus=campaignTransactionalSaleProcess
                .handleCheckCampaignProcess(checkCampaignStatusRequest.toModel());

        return CheckCodeStatusResponse.fromModel(checkSaleStatus);
    }


    @PostMapping(value = "/notify-sale-campaign")
    public NotifySaleCampaignResponse saveNotifySaleCampaign(@RequestBody NotifySaleCampaignRequest notifySaleCampaignRequest) {

        var saleInformation=campaignTransactionalSaleProcess
                .handleNotifyProcess(notifySaleCampaignRequest.toModel());

        return NotifySaleCampaignResponse.fromModel(saleInformation);

    }

    @PostMapping(value = "/remove-contact-sale-reward")
    public RemoveSaleCampaignResponse deleteSaleCampaign(@RequestBody RemoveSaleRewardRequest removeSaleRewardRequest) {


        return RemoveSaleCampaignResponse
                .fromModel(saleCampaignFacade
                        .removeSale(removeSaleRewardRequest.toModel()));

    }


    @PostMapping("/validate-contact-campaign")
    public ContactCampaignsResponse.CampaignResponse validateCampaignsOnContactParams(@RequestBody @Valid ValidateContactCampaignRequest validateContactCampaignRequest) {

        var campaign = contactFacade.validateCampaignForProduct(validateContactCampaignRequest.toModel());

        return ContactCampaignsResponse.toCampaignResponseModel(campaign);

    }


}
