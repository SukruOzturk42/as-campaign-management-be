package com.anadolusigorta.campaignmanagement.domain.campaigngoal.model;

import com.anadolusigorta.campaignmanagement.domain.campaigngoal.model.CampaignGoalType;
import com.anadolusigorta.campaignmanagement.domain.campaigngoal.model.CampaignPolicyType;
import com.anadolusigorta.campaignmanagement.domain.campaignsalechannel.model.CampaignSaleChannelType;
import com.anadolusigorta.campaignmanagement.domain.referencetype.model.AttributeReferenceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignGoal {

    private Long id;

    private LocalDateTime updatedAt;

    private CampaignGoalType goalType;

    private AttributeReferenceType referenceType;

    private Long amountGoal;

    private Long policyGoal;

    private Long campaignId;

    private Long insuredGoal;

}
