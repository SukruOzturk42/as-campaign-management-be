package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignReason;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignReasonResponse {

    private Long id;

    private String decidingOrganization;

    private String decisionNumber;

    private LocalDateTime decisionDate;

    private LocalDateTime createdDate;

    private String decisionDescription;

    private Long campaignId;

    public static CampaignReasonResponse fromModel(CampaignReason campaignReason) {
        return CampaignReasonResponse.builder()
                .id(campaignReason.getId())
                .decidingOrganization(campaignReason.getDecidingOrganization())
                .decisionNumber(campaignReason.getDecisionNumber())
                .decisionDate(campaignReason.getDecisionDate())
                .createdDate(campaignReason.getCreatedDate())
                .decisionDescription(campaignReason.getDecisionDescription())
                .campaignId(campaignReason.getCampaignId())
                .build();
    }

    public static List<CampaignReasonResponse> fromListOfModel(List<CampaignReason> campaignReasons) {
        return campaignReasons.stream().map(CampaignReasonResponse::fromModel).collect(Collectors.toList());
    }

}
