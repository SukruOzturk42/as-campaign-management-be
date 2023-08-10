package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CreateCampaign;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CreateCampaignAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignAttributeRequest {

    private Long id;

    private Long structureId;

    private Long attributeId;

    public CreateCampaignAttribute toModel(){
        return CreateCampaignAttribute.builder()
                .id(id)
                .attributeId(attributeId)
                .structureId(structureId)
                .build();
    }
}
