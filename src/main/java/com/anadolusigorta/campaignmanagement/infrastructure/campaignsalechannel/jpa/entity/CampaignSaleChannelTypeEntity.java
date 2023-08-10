package com.anadolusigorta.campaignmanagement.infrastructure.campaignsalechannel.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaigngoal.model.CampaignGoalType;
import com.anadolusigorta.campaignmanagement.domain.campaignsalechannel.model.CampaignSaleChannelType;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "campaign_sale_channel_type")
public class CampaignSaleChannelTypeEntity extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public CampaignSaleChannelType toModel() {
        return CampaignSaleChannelType.builder()
                .id(super.getId())
                .name(name)
                .description(description)
                .build();
    }
}
