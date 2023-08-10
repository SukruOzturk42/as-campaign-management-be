package com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignSearch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignsSearchRequest {
    @NotNull
    private List<Long> campaignTypes;

    private Map<String,Object> parameters;


    public CampaignSearch toModel() {
        return CampaignSearch.builder()
                .campaignTypes(campaignTypes)
                .parameters(parameters)
                .build();
    }

}
