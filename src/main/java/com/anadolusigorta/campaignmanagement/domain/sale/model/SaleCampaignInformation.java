package com.anadolusigorta.campaignmanagement.domain.sale.model;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignInformation;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRuleGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleCampaignInformation {

    private CampaignInformation campaignInformation;
    private long totalSaleCount;
    private long totalProposalCount;

}
