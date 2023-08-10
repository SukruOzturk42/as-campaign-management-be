package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.UsedCodeGroupInformation;
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
public class UsedCodeGroupInformationResponse {

    private Long campaignId;

    private Long campaignVersion;

    private String campaignName;

    public static UsedCodeGroupInformationResponse fromModel(UsedCodeGroupInformation usedCodeGroupInformation) {
        return UsedCodeGroupInformationResponse.builder()
                .campaignId(usedCodeGroupInformation.getCampaignId())
                .campaignVersion(usedCodeGroupInformation.getCampaignVersion())
                .campaignName(usedCodeGroupInformation.getCampaignName())
                .build();
    }

    public static List<UsedCodeGroupInformationResponse> fromListOfModel(List<UsedCodeGroupInformation> usedCodeGroupInformationList) {
        return usedCodeGroupInformationList.stream().map(UsedCodeGroupInformationResponse::fromModel).collect(Collectors.toList());
    }

}
