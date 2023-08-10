package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignGroup;
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
@Table(name = "campaign_group")
public class CampaignGroupEntity extends AbstractEntity {

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", nullable = false, length = 400)
    private String description;

    public CampaignGroup toModel() {
        return CampaignGroup.builder()
                .id(super.getId())
                .name(name)
                .description(description)
                .build();
    }

    public static CampaignGroupEntity fromModel(CampaignGroup campaignGroup) {
        return CampaignGroupEntity.builder()
                .name(campaignGroup.getName())
                .description(campaignGroup.getDescription())
                .build();
    }

}
