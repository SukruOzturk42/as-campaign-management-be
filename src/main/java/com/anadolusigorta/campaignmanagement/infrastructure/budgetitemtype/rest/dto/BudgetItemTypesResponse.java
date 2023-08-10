package com.anadolusigorta.campaignmanagement.infrastructure.budgetitemtype.rest.dto;

import com.anadolusigorta.campaignmanagement.domain.budgetitemtype.model.BudgetITemType;
import com.anadolusigorta.campaignmanagement.domain.campaignbudget.model.CampaignBudget;
import com.anadolusigorta.campaignmanagement.domain.operator.ConjunctionOperatorEnum;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignbudget.rest.dto.response.CampaignBudgetResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.rule.rest.dto.request.CreateRuleRequest;
import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BudgetItemTypesResponse {

    private List<BudgetItemTypeResponse> budgetItemTypeResponses;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class BudgetItemTypeResponse {
        private Long id;

        private String name;

        private String description;

        public static BudgetItemTypeResponse fromModel(BudgetITemType budgetITemType) {
            return BudgetItemTypeResponse.builder()
                    .id(budgetITemType.getId())
                    .name(budgetITemType.getName())
                    .description(budgetITemType.getDescription())
                    .build();
        }


    }

    public static List<BudgetItemTypeResponse> fromModel(List<BudgetITemType> budgetITemTypes){
        return budgetITemTypes.stream().map(BudgetItemTypeResponse::fromModel).collect(Collectors.toList());
    }
}
