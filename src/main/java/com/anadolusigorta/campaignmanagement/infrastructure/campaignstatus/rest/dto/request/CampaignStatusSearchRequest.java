package com.anadolusigorta.campaignmanagement.infrastructure.campaignstatus.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignStatusSearchRequest {

    private CampaignStatusTypeEnum campaignStatusTypeEnum;

    private String campaignSearchStatusEnum;

    private String campaignSearchApprovalEnum;

    private Long campaignStatusId;

    private Long campaignApprovalStatusId;

    private Long campaignTypeId;

    /*public CampaignStatusSearch toModel() {
        return CampaignStatusSearch.builder()
                .campaignStatusType(campaignStatusType)
                .campaignStatus(campaignStatus)
                .campaignApprovalStatus(campaignApprovalStatus)
                .build();
    }*/

}
