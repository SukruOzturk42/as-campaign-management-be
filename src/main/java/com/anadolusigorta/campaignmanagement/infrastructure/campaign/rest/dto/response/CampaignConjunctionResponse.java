package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignConjunction;
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
public class CampaignConjunctionResponse {

    private Long id;

    private String name;

    private String description;

    public static CampaignConjunctionResponse fromModel(CampaignConjunction campaignConjunction) {
        return CampaignConjunctionResponse.builder()
                .id(campaignConjunction.getId())
                .name(campaignConjunction.getName())
                .description(campaignConjunction.getDescription())
                .build();
    }

    public static List<CampaignConjunctionResponse> fromListOfModel(List<CampaignConjunction> campaignConjunctionList) {
        return campaignConjunctionList.stream().map(CampaignConjunctionResponse::fromModel).collect(Collectors.toList());
    }

}
