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
public class CampaignGroupRequest {

    private Long id;

    private String name;

    private String description;

    public CampaignGroup toModel() {
        return CampaignGroup.builder()
                .id(id)
                .name(name)
                .description(description)
                .build();
    }

}
