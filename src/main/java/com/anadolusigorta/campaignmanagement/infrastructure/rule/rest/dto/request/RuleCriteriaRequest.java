package com.anadolusigorta.campaignmanagement.infrastructure.rule.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.rule.model.CreateRule;
import com.anadolusigorta.campaignmanagement.domain.rule.model.RuleCriteria;
import com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.entity.RuleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.digester.Rule;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleCriteriaRequest {

    private Long ruleId;

    private String ruleName;


    public RuleCriteria toModel(){
        return RuleCriteria.builder()
                .ruleId(ruleId)
                .ruleName(ruleName)
                .build();
    }

}
