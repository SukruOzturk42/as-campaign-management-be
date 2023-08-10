package com.anadolusigorta.campaignmanagement.domain.campaignsalechannel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignSaleChannelType {

    private Long id;

    private String name;

    private String description;

}
