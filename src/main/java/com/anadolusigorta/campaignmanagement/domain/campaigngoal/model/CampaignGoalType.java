package com.anadolusigorta.campaignmanagement.domain.campaigngoal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignGoalType {

    private Long id;

    private String name;

    private String description;

}
