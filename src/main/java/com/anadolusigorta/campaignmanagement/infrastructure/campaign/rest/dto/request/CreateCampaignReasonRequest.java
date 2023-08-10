package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignReason;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCampaignReasonRequest {

    private Long id;

    @NotNull
    private String decidingOrganization;

    private String decisionNumber;

    @NotNull
    private LocalDateTime decisionDate;

    private String decisionDescription;

    private Long campaignId;

    public CampaignReason toModel() {
        return CampaignReason.builder()
                .id(id)
                .decidingOrganization(decidingOrganization)
                .decisionNumber(decisionNumber)
                .decisionDate(decisionDate)
                .decisionDescription(decisionDescription)
                .campaignId(campaignId)
                .build();
    }

}
