package com.anadolusigorta.campaignmanagement.domain.rule.facade;

import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.rule.model.CreateRule;
import com.anadolusigorta.campaignmanagement.domain.rule.model.RuleCriteria;
import com.anadolusigorta.campaignmanagement.domain.rule.port.RuleRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.entity.RuleEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.rule.rest.dto.request.CreateRuleRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.rule.rest.dto.request.RuleCriteriaRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RuleFacade {

    private final RuleRepository ruleRepository;

    public void saveRule(CreateRule createRule) {
        ruleRepository.saveRule(createRule);
    }

    public List<RuleEntity> getRules() {
        return ruleRepository.getRules();
    }

    public PageContent<RuleEntity> getPageableRules(RuleCriteria ruleCriteria, Pageable pageable) {
        return ruleRepository.getPageableRules(ruleCriteria,pageable);
    }
    public List<RuleEntity> getRulesByCampaignTypeId(Long campaignTypeId){
        return ruleRepository.getRulesByCampaignTypeId(campaignTypeId);
    }

    public RuleEntity  getRuleByRuleId(Long ruleId) {
        return ruleRepository.getRuleByRuleId(ruleId);
    }

}
