package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCampaignGroupRequest {


    private String name;

    public CampaignGroup toModel() {
        return CampaignGroup.builder()
                .name(name)
                .build();
    }

}
