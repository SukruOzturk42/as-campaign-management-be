package com.anadolusigorta.campaignmanagement.domain.campaign.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignReason {

    private Long id;

    private String decidingOrganization;

    private String decisionNumber;

    private LocalDateTime decisionDate;

    private LocalDateTime createdDate;

    private String decisionDescription;

    private Long campaignId;

}
