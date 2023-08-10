package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.campaigngoal.model.CampaignGoal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCampaignGoalRequest {

    private Long id;

    private Long campaignId;

    public CampaignGoal toModel() {
        return CampaignGoal.builder()
                .id(id)
                .campaignId(campaignId)
                .build();
    }
}
