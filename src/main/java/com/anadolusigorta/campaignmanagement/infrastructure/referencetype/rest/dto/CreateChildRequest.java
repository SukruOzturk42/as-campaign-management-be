package com.anadolusigorta.campaignmanagement.infrastructure.referencetype.rest.dto;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateChildRequest {

    private String childName;

    private String childDescription;

    public CampaignGroup toModel() {
        return CampaignGroup.builder()
                .name(childName != null ? childName : null)
                .description(childDescription != null ? childDescription : null)
                .build();
    }
}
