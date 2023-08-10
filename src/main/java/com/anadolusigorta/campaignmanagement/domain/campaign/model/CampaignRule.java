package com.anadolusigorta.campaignmanagement.domain.campaign.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignRule {

    private Long campaignId;

    private Long campaignVersion;

    private String campaignName;

}
