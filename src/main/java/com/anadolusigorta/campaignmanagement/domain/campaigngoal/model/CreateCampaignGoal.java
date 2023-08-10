package com.anadolusigorta.campaignmanagement.domain.campaigngoal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCampaignGoal {

    private Long id;

    private Long goalTypeId;

    private Long referenceTypeId;

    private Long saleChannelTypeId;

    private Long policyTypeId;

    private Long amountGoal;

    private Long policyGoal;

    private Long campaignId;

    private Long insuredGoal;

}
