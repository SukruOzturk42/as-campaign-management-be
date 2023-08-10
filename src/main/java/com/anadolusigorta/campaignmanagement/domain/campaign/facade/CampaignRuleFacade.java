package com.anadolusigorta.campaignmanagement.domain.campaign.facade;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRule;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.CampaignRuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignRuleFacade {

    private final CampaignRuleRepository campaignRuleRepository;

    public List<CampaignRule> getCampaignRulesByRuleId(Long ruleId) {
        return campaignRuleRepository.findCampaignRulesByRuleId(ruleId);
    }
}
