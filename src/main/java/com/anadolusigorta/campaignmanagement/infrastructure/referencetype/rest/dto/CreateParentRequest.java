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
public class CreateParentRequest {

    private String parentName;

    private String parentDescription;

    public CampaignGroup toModel() {
        return CampaignGroup.builder()
                .name(parentName != null ? parentName : null)
                .description(parentDescription != null ? parentDescription : null)
                .build();
    }

}
