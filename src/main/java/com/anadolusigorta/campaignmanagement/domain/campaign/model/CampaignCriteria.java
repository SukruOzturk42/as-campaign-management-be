package com.anadolusigorta.campaignmanagement.domain.campaign.model;

import com.anadolusigorta.campaignmanagement.domain.operator.OperatorEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CampaignCriteria {

    private Long campaignId;

    private String campaignName;

    private String campaignStatusType;

    private Long campaignTypeId;

    private Long attributeId;

    private List<String> value;

    private OperatorEnum operator;


}
