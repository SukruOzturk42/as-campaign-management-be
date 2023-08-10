package com.anadolusigorta.campaignmanagement.infrastructure.campaignbudget.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.campaignbudget.model.CreateCampaignBudget;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCampaignBudgetRequest {

    private Long id;

    private LocalDateTime budgetDate;

    @NotNull
    private Long budgetItemId;

    @Min(1)
    @NotNull
    private Long budgetAmount;

    @NotNull
    private Long campaignId;


    public CreateCampaignBudget toModel() {
       return CreateCampaignBudget.builder().id(id)
                .campaignId(campaignId)
                .budgetItemId(budgetItemId)
                .budgetAmount(budgetAmount)
                .budgetDate(budgetDate).build();
    }
}
