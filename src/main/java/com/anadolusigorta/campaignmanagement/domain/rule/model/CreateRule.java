package com.anadolusigorta.campaignmanagement.domain.rule.model;

import com.anadolusigorta.campaignmanagement.domain.attribute.model.AttributeTypeEnum;
import com.anadolusigorta.campaignmanagement.domain.operator.ConjunctionOperatorEnum;
import com.anadolusigorta.campaignmanagement.domain.operator.OperatorEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateRule {

    private List<RuleGroup> ruleGroups;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RuleGroup {
        private Long ruleGroupId;
        private String name;
        private Long campaignTypeId;
        private ConjunctionOperatorEnum conjunctionOperator;
        private List<RuleAttribute> attributes;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RuleAttribute {

        private Long attributeId;
        private OperatorEnum operator;
        private AttributeTypeEnum type;
        private List<String> value;
    }



}
