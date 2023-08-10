package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignCriteria;
import com.anadolusigorta.campaignmanagement.domain.operator.OperatorEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApprovalCampaignCriteriaRequest {

    private Long campaignId;

    private String campaignName;

    private String approvalCampaignSearchStatusEnum;

    private Long campaignTypeId;

    private Long attributeId;

    private List<String> value;
    private OperatorEnum operator;

    public CampaignCriteria toModel(){
        return CampaignCriteria.builder()
                .campaignName(campaignName)
                .campaignId(campaignId)
                .campaignTypeId(campaignTypeId)
                .campaignStatusType(approvalCampaignSearchStatusEnum)
                .operator(operator)
                .attributeId(attributeId)
                .value(value)
                .build();
    }
}
