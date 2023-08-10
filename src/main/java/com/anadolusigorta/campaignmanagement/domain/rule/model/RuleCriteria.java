package com.anadolusigorta.campaignmanagement.domain.rule.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RuleCriteria {

    private Long ruleId;

    private String ruleName;

}
