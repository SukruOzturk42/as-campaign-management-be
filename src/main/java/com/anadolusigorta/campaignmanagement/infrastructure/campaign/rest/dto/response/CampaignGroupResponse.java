package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignGroup;
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
public class CampaignGroupResponse {

    private Long id;

    private String name;

    private String description;

    public static CampaignGroupResponse fromModel(CampaignGroup campaignGroup) {
        return CampaignGroupResponse.builder()
                .id(campaignGroup.getId())
                .name(campaignGroup.getName())
                .description(campaignGroup.getDescription())
                .build();
    }

    public static List<CampaignGroupResponse> fromListOfModel(List<CampaignGroup> campaignGroupList) {
        return campaignGroupList.stream().map(CampaignGroupResponse::fromModel).collect(Collectors.toList());
    }
}
