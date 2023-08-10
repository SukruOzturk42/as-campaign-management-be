package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignTypeRequest {

    private Long id;

    private String name;

    private String description;

    public CampaignType toModel() {
        return CampaignType.builder()
                .id(id)
                .name(name)
                .description(description)
                .build();
    }

}
