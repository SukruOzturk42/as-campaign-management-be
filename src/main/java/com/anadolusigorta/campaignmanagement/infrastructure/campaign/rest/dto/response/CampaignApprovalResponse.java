package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignApproval;
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
public class CampaignApprovalResponse {

    private Long id;

    private String username;

    private String description;

    public static CampaignApprovalResponse fromModel(CampaignApproval campaignApproval) {
        return CampaignApprovalResponse.builder()
                .id(campaignApproval.getId())
                .username(campaignApproval.getUsername())
                .build();
    }

    public static List<CampaignApprovalResponse> fromListOfModel(List<CampaignApproval> campaignApprovalList) {
        return campaignApprovalList.stream()
                .map(CampaignApprovalResponse::fromModel)
                .collect(Collectors.toList());
    }

}
