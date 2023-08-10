package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.Campaign;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CreateCampaign;
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
public class CreateCampaignResponse {

    private Long id;

    public static CreateCampaignResponse fromModel(Campaign createCampaign) {
        return CreateCampaignResponse.builder()
                .id(createCampaign.getId())
                .build();
    }

    public static List<CreateCampaignResponse> fromListOfModel(List<Campaign> campaigns) {
        return campaigns.stream().map(CreateCampaignResponse::fromModel).collect(Collectors.toList());
    }
}
