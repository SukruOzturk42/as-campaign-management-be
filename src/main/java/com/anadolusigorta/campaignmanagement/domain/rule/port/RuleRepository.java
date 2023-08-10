package com.anadolusigorta.campaignmanagement.domain.rule.port;

import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.rule.model.CreateRule;
import com.anadolusigorta.campaignmanagement.domain.rule.model.RuleCriteria;
import com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.entity.RuleEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.rule.rest.dto.request.RuleCriteriaRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RuleRepository {

    void saveRule(CreateRule createRule);

    List<RuleEntity> getRules();

    PageContent<RuleEntity> getPageableRules(RuleCriteria createRule , Pageable pageable);

    List<RuleEntity> getRulesByCampaignTypeId(Long campaignTypeId);

    RuleEntity getRuleByRuleId(Long ruleId);

}
