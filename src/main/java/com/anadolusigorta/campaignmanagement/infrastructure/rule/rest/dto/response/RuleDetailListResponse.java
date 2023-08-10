package com.anadolusigorta.campaignmanagement.infrastructure.rule.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleDetailListResponse {

    private String conjunctionOperator;

    private String ruleName;

    private List<RuleDetailResponse> ruleDetailResponseList;

}
