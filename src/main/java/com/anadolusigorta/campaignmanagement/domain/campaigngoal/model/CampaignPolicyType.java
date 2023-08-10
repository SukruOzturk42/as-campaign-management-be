package com.anadolusigorta.campaignmanagement.domain.campaigngoal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignPolicyType {

    private long id;

    private Long policyCode;

    private Long policyType;

    private String name;

    private String description;

}
