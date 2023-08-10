package com.anadolusigorta.campaignmanagement.domain.campaignstatus.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignStatusSearch {

    private CampaignStatusTypeEnum campaignStatusType;

    private CampaignStatusEnum campaignStatus;

    private CampaignApprovalStatusEnum campaignApprovalStatus;

}
