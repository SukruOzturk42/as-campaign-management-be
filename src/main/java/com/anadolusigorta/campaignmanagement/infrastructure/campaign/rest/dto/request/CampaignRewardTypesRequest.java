package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignRewardTypesRequest {

    private Long campaignId;

    private Long campaignRuleGroupId;

}
