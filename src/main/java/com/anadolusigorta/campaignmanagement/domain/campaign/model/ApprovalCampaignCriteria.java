package com.anadolusigorta.campaignmanagement.domain.campaign.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApprovalCampaignCriteria {

    private Long campaignId;

    private String campaignName;

    private String approvalCampaignSearchStatusEnum;

    private Long campaignTypeId;

    private String attributeName;

    private List<String> attributeValue;

}
