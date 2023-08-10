package com.anadolusigorta.campaignmanagement.domain.campaign.port;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRule;

import java.util.List;

public interface CampaignRuleRepository {

    public List<CampaignRule> findCampaignRulesByRuleId(Long ruleId);

}
