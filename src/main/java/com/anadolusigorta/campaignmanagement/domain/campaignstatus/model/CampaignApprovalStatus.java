package com.anadolusigorta.campaignmanagement.domain.campaignstatus.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignApprovalStatus implements Serializable {

    private Long id;

    private String name;

    private CampaignApprovalStatusEnum approvalStatus;

    private Long code;

}
