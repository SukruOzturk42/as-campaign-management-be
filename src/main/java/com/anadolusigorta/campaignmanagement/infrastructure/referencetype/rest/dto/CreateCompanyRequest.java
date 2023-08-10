package com.anadolusigorta.campaignmanagement.infrastructure.referencetype.rest.dto;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignGroup;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response.CampaignGroupResponse;
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
public class CreateCompanyRequest {

    private String companyName;

    private String companyDescription;

    private Boolean isMilitary;

    public CampaignGroup toModel() {
        return CampaignGroup.builder()
                .name(companyName != null ? companyName : null)
                .description(companyDescription != null ? companyDescription : null)
                .isMilitary(isMilitary != null ? isMilitary : null)
                .build();
    }
}
