package com.anadolusigorta.campaignmanagement.domain.campaignbudget.model;

import com.anadolusigorta.campaignmanagement.domain.budgetitemtype.model.BudgetITemType;
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
public class CampaignBudget {

    private Long id;

    private LocalDateTime budgetDate;

    private BudgetITemType budgetItemType;

    private Long budgetAmount;

    private LocalDateTime createdAt;

    private Long campaignId;

    private List<CampaignBudgetDetail> campaignBudgetDetailList;

}
