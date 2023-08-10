package com.anadolusigorta.campaignmanagement.domain.sale.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleInformationSummary {

    private Long campaignId;
    private String campaignName;
    private int totalProposalCount;
    private int totalSaleCount;
    private Boolean isTriggeredRewardSend;
    private Boolean isStartedRewardSend;

}
