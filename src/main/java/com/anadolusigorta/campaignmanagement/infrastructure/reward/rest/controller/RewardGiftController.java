package com.anadolusigorta.campaignmanagement.infrastructure.reward.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.reward.facade.RewardGiftFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.rest.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@ApiManagementController
@RequiredArgsConstructor
public class RewardGiftController extends BaseController {

    private final RewardGiftFacade rewardGiftFacade;

    @GetMapping("reward-gift-types")
    public Response<?> getGiftTypes() {
        return respond(RewardGiftTypeResponse.fromListOfModel(rewardGiftFacade.getRewardTypes()));
    }

    @GetMapping("reward-gift-payment-types")
    public Response<?> getGiftPaymentTypes() {
        return respond(RewardGiftPaymentTypeResponse.fromListOfModel(rewardGiftFacade.getRewardGiftPaymentTypes()));
    }

    @GetMapping("reward-gift-delivery-types")
    public Response<?> getGiftDeliveryTypes() {
        return respond(RewardGiftDeliveryTypeResponse.fromListOfModel(rewardGiftFacade.getRewardGiftDeliveryTypes()));
    }

    @GetMapping("reward-gift-delivery-start-types")
    public Response<?> getGiftDeliveryStartTypes(@RequestParam(value = "campaignTypeId") Long campaignTypeId) {
        return respond(RewardDeliveryStartTypeResponse.fromListOfModel(rewardGiftFacade.getRewardGiftDeliveryStartTypes(campaignTypeId)));
    }

    @GetMapping("reward-gift-kinds")
    public Response<?> getGiftKinds() {
        return respond(RewardGiftKindResponse.fromListOfModel(rewardGiftFacade.getRewardGiftKinds()));
    }

    @GetMapping("reward-companies-information")
    public Response<?> getRewardCompanyInformation() {
        return respond(RewardCompanyInformationResponse.fromListOfModel(rewardGiftFacade.getRewardCompanyInformation()));
    }

    @PostMapping("reward-companies-information")
    public Response<?> saveRewardCompanyInformation(@RequestBody CreateRewardCompanyInformation createRewardCompanyInformation) {
        return respond(RewardCompanyInformationResponse.fromModel(rewardGiftFacade.saveRewardCompanyInformation(createRewardCompanyInformation.toModel())));
    }

    @GetMapping("reward-gift-products")
    public Response<?> getRewardGiftProducts() {
        return respond(RewardGiftProductResponse.fromListOfModel(rewardGiftFacade.getRewardGiftProducts()));
    }

    @GetMapping("cm-policy-sale/reward-send-method-types")
    public Response<?> getRewardSendMethodTypes() {
        return respond(RewardGiftSendMethodTypeResponse.fromListOfModel(rewardGiftFacade.getRewardGiftMethodTypes()));
    }

    @GetMapping("reward-gift-tickets")
    public Response<?> getRewardTickets() {
        return respond(RewardTicketTypeResponse.fromListOfModel(rewardGiftFacade.getRewardTicketTypes()));
    }

    @PostMapping("reward-gift-product")
    public Response<?> saveRewardGiftProduct(@RequestBody CreateRewardGiftProductRequest createRewardGiftProductRequest) {
        return respond(RewardGiftProductResponse.fromModel(rewardGiftFacade
                .createRewardGiftProduct(createRewardGiftProductRequest.toModel())));
    }

    @PostMapping("reward-gift-ticket-type")
    public Response<?> saveRewardGiftTicket(@RequestBody CreateRewardTicketRequest createRewardTicketRequest) {
        return respond(RewardTicketTypeResponse.fromModel(rewardGiftFacade
                .saveRewardGiftTicketType(createRewardTicketRequest.toModel())));
    }

    @GetMapping("reward-role-type")
    public List<RewardRoleResponse> getRewardRoleTypes() {
        return RewardRoleResponse.fromListOfModel(rewardGiftFacade.getRewardRoles());
    }




}
