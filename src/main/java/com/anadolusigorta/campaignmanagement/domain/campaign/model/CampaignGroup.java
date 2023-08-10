package com.anadolusigorta.campaignmanagement.domain.campaign.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignGroup implements Serializable {

    private Long id;

    private String name;

    private String description;

    private Boolean isMilitary;

}
