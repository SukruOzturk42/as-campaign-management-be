package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignVersion;
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
public class CampaignVersionResponse {

    private Long id;

    private Long version;

    private Boolean isActiveVersion;


    public static CampaignVersionResponse fromModel(CampaignVersion campaignVersion) {
        return CampaignVersionResponse.builder()
                .id(campaignVersion.getId())
                .version(campaignVersion.getVersion())
                .isActiveVersion(campaignVersion.getIsActiveVersion())
                .build();
    }

    public static List<CampaignVersionResponse> fromListOfModel(List<CampaignVersion> campaignVersions) {
        return campaignVersions.stream().map(CampaignVersionResponse::fromModel).collect(Collectors.toList());
    }
}
