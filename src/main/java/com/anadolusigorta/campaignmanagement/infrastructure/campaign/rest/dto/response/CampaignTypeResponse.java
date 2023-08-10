package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignType;
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
public class CampaignTypeResponse {

    private Long id;

    private String name;

    private String description;


    public static CampaignTypeResponse fromModel(CampaignType campaignType) {
        return CampaignTypeResponse.builder()
                .id(campaignType.getId())
                .name(campaignType.getName())
                .description(campaignType.getDescription())
                .build();
    }

    public static List<CampaignTypeResponse> fromListOfModel(List<CampaignType> campaigns) {
        return campaigns.stream().map(CampaignTypeResponse::fromModel).collect(Collectors.toList());
    }
}
