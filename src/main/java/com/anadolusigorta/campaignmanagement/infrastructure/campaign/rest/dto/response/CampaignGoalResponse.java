package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaigngoal.model.CampaignGoal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignGoalResponse {

    private Long id;

    private String goalTypeName;

    private String goalType;

    private Long goalTypeId;

    private String referenceTypeDescription;

    private Long referenceTypeId;

    private Long amountGoal;

    private Long policyGoal;

    private Long campaignId;

    private Long insuredGoal;

    private LocalDateTime updatedAt;

    public static CampaignGoalResponse fromModel(CampaignGoal campaignGoal) {
        return CampaignGoalResponse.builder()
                .id(campaignGoal.getId())
                .goalTypeName(campaignGoal.getGoalType().getName())
                .goalType(campaignGoal.getGoalType().getDescription())
                .goalTypeId(campaignGoal.getGoalType().getId())
                .referenceTypeDescription(campaignGoal.getReferenceType()  != null ?
                        campaignGoal.getReferenceType().getDescription() : null)
                .referenceTypeId(campaignGoal.getReferenceType() != null ?
                        campaignGoal.getReferenceType().getId() : null)
                .amountGoal(campaignGoal.getAmountGoal())
                .policyGoal(campaignGoal.getPolicyGoal())
                .updatedAt(campaignGoal.getUpdatedAt())
                .campaignId(campaignGoal.getCampaignId())
                .insuredGoal(campaignGoal.getInsuredGoal())
                .build();
    }

    public static List<CampaignGoalResponse> fromListOfModel(List<CampaignGoal> campaignGoals) {
        return campaignGoals.stream().map(CampaignGoalResponse::fromModel).collect(Collectors.toList());
    }

}
