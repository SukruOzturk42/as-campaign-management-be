package com.anadolusigorta.campaignmanagement.infrastructure.campaignbudget.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.budgetitemtype.model.BudgetITemType;
import com.anadolusigorta.campaignmanagement.domain.campaignbudget.model.CampaignBudget;
import com.anadolusigorta.campaignmanagement.domain.campaignbudget.model.CampaignBudgetDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignBudgetResponse {

    private Long id;

    private LocalDateTime budgetDate;

    private String budgetItemType;

    private Long budgetAmount;

    private LocalDateTime createdAt;

    private Long campaignId;

    private Long budgetItemId;

    private List<CampaignBudgetDetail> campaignBudgetDetail;

    public static CampaignBudgetResponse fromModel(CampaignBudget campaignBudget) {
        return CampaignBudgetResponse.builder()
                .id(campaignBudget.getId())
                .budgetAmount(campaignBudget.getBudgetAmount())
                .budgetDate(campaignBudget.getBudgetDate())
                .campaignId(campaignBudget.getCampaignId())
                .createdAt(campaignBudget.getCreatedAt())
                .budgetItemType(campaignBudget.getBudgetItemType().getDescription())
                .budgetItemId(campaignBudget.getBudgetItemType().getId())
                .campaignBudgetDetail(campaignBudget.getCampaignBudgetDetailList())
                .build();
    }

}
