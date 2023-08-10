package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCampaignDetailRequest {

    private Long id;

    private Long campaignId;

    public CampaignDetail toModel() {
        return CampaignDetail.builder()
                .id(id)
                .campaignId(campaignId)
                .build();
    }

}
