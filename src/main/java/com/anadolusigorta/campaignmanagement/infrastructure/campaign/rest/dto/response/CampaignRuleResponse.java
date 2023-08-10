package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRule;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.UsedCodeGroupInformation;
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
public class CampaignRuleResponse {

    private Long campaignId;

    private Long campaignVersion;

    private String campaignName;

    public static CampaignRuleResponse fromModel(CampaignRule campaignRule) {
        return CampaignRuleResponse.builder()
                .campaignId(campaignRule.getCampaignId())
                .campaignVersion(campaignRule.getCampaignVersion())
                .campaignName(campaignRule.getCampaignName())
                .build();
    }

    public static List<CampaignRuleResponse> fromListOfModel(List<CampaignRule> campaignRules) {
        return campaignRules.stream().map(CampaignRuleResponse::fromModel).collect(Collectors.toList());
    }

}
