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
public class CampaignBudgetDetail {

    private Long id;

    private String updater;

    private String description;

    private LocalDateTime createdAt;
}
