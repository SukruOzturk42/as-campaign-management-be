package com.anadolusigorta.campaignmanagement.infrastructure.campaigngoal.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaigngoal.model.CampaignGoalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignGoalTypeResponse {

    private Long id;

    private String name;

    private String description;

    public static CampaignGoalTypeResponse fromModel(CampaignGoalType campaignGoalType) {
        return CampaignGoalTypeResponse.builder()
                .id(campaignGoalType.getId())
                .name(campaignGoalType.getName())
                .description(campaignGoalType.getDescription())
                .build();
    }

    public static List<CampaignGoalTypeResponse> fromListOfModel(List<CampaignGoalType> campaignGoalTypes) {
        return campaignGoalTypes.stream().map(CampaignGoalTypeResponse::fromModel).collect(Collectors.toList());
    }

}
