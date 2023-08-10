package com.anadolusigorta.campaignmanagement.infrastructure.campaignbudget.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaignbudget.model.CampaignBudget;

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
public class CampaignBudgetListResponse {
    private List<CampaignBudgetResponse> campaignBudgets;

    public static List<CampaignBudgetResponse> fromModel(List<CampaignBudget> campaignBudgets){
        return campaignBudgets.stream().map(CampaignBudgetResponse::fromModel).collect(Collectors.toList());
    }

}
