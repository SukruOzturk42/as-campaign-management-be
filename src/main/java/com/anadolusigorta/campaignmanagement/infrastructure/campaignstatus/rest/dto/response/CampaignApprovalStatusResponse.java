package com.anadolusigorta.campaignmanagement.infrastructure.campaignstatus.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignApprovalStatus;
import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignApprovalStatusEnum;
import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignStatus;
import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class CampaignApprovalStatusResponse {

    private Long id;

    private String name;

    private CampaignApprovalStatusEnum campaignApprovalStatus;

    public static CampaignApprovalStatus fromModel(CampaignApprovalStatus campaignStatus) {
        return CampaignApprovalStatus.builder()
                .id(campaignStatus.getId())
                .name(campaignStatus.getName())
                .approvalStatus(campaignStatus.getApprovalStatus())
                .build();
    }

}
