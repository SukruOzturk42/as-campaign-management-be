package com.anadolusigorta.campaignmanagement.domain.campaign.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignVersion {
    private Long id;

    private Long version;

    private Boolean isActiveVersion;

    private CampaignInformation campaignInformation;

    private List<CampaignRuleGroup> campaignRuleGroups;

}
