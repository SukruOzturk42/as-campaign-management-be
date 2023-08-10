package com.anadolusigorta.campaignmanagement.infrastructure.rule.rest.dto.request;


import com.anadolusigorta.campaignmanagement.domain.attribute.model.AttributeTypeEnum;
import com.anadolusigorta.campaignmanagement.domain.operator.ConjunctionOperatorEnum;
import com.anadolusigorta.campaignmanagement.domain.operator.OperatorEnum;
import com.anadolusigorta.campaignmanagement.domain.rule.model.CreateRule;
import com.anadolusigorta.campaignmanagement.domain.rule.model.CreateRule.*;
import com.anadolusigorta.campaignmanagement.domain.rule.model.RuleCriteria;
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
public class CreateRuleRequest {

    private List<RuleGroupRequest> ruleGroups;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class RuleGroupRequest {

        private String ruleName;

        private Long campaignTypeId;

        private ConjunctionOperatorEnum conjunctionOperator;

        private List<RuleAttributeRequest> attributes;

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class RuleAttributeRequest {

        private Long attributeId;

        private OperatorEnum operator;

        private AttributeTypeEnum type;

        private List<String> value;

    }

    public  CreateRule toModel(){
        return CreateRule.builder().ruleGroups(ruleGroups.stream()
                .map(this::mapToRuleGroupModel)
                .collect(Collectors.toList())).build();
    }

    private  RuleGroup mapToRuleGroupModel(RuleGroupRequest ruleGroupRequest){
        return RuleGroup.builder()
                .name(ruleGroupRequest.ruleName)
                .campaignTypeId(ruleGroupRequest.campaignTypeId)
                .attributes(ruleGroupRequest
                        .attributes.stream()
                        .map(this::mapToCampaignAttributeModel)
                        .collect(Collectors.toList()))
                .conjunctionOperator(ruleGroupRequest.conjunctionOperator)
                .build();
    }

    private RuleAttribute mapToCampaignAttributeModel(RuleAttributeRequest ruleAttributeRequest){
        return RuleAttribute.builder()
                .attributeId(ruleAttributeRequest.attributeId)
                .operator(ruleAttributeRequest.operator)
                .type(ruleAttributeRequest.type)
                .value(ruleAttributeRequest.value)
                .build();
    }
}
