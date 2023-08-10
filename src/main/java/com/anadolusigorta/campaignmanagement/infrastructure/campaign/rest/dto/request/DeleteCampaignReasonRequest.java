package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignReason;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCampaignReasonRequest {

    private Long id;

    private Long campaignId;

    public CampaignReason toModel() {
        return CampaignReason.builder()
                .id(id)
                .campaignId(campaignId)
                .build();
    }

}
