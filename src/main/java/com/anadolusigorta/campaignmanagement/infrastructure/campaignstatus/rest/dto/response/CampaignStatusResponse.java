package com.anadolusigorta.campaignmanagement.infrastructure.campaignstatus.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignStatus;
import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class CampaignStatusResponse {

    private Long id;

    private String name;

    private CampaignStatusEnum campaignStatus;

    public static CampaignStatusResponse fromModel(CampaignStatus campaignStatus) {
        return CampaignStatusResponse.builder()
                .id(campaignStatus.getId())
                .name(campaignStatus.getName())
                .campaignStatus(campaignStatus.getStatus())
                .build();
    }
}
