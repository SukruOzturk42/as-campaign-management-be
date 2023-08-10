package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.campaigngoal.model.CreateCampaignGoal;
import com.anadolusigorta.campaignmanagement.infrastructure.validation.conditionalvalidation.ConditionalValidation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ConditionalValidation(
        conditionalProperty = "goalTypeId", values = {"2","3"},
        requiredProperties = {"referenceTypeId"},
        message = "Hedef Parametresi Seçiniz.")
public class CreateCampaignGoalRequest {

    private Long id;

    @NotNull
    private Long goalTypeId;

    private Long referenceTypeId;

    private Long saleChannelTypeId;

    private Long policyTypeId;

    private Long amountGoal;

    private Long policyGoal;

    private Long campaignId;

    private Long insuredGoal;

    public CreateCampaignGoal toModel() {
        return CreateCampaignGoal.builder()
                .id(id)
                .goalTypeId(goalTypeId)
                .referenceTypeId(referenceTypeId)
                .saleChannelTypeId(saleChannelTypeId)
                .policyTypeId(policyTypeId)
                .amountGoal(amountGoal)
                .policyGoal(policyGoal)
                .campaignId(campaignId)
                .insuredGoal(insuredGoal)
                .build();
    }

}
