package com.anadolusigorta.campaignmanagement.domain.campaignbudget.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCampaignBudget {

    private Long id;

    private LocalDateTime budgetDate;

    private Long budgetItemId;

    private Long budgetAmount;

    private Long campaignId;
}
