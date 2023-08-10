package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity;


import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignConjunction;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.company.jpa.entity.CompanyEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "campaign_conjunction")
public class CampaignConjunctionEntity extends AbstractEntity {

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", nullable = false, length = 400)
    private String description;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;

    public CampaignConjunction toModel() {
        return CampaignConjunction.builder()
                .id(super.getId())
                .name(name)
                .description(description)
                .build();
    }

    public static CampaignConjunctionEntity fromModel(CampaignConjunction campaignConjunction) {
        return CampaignConjunctionEntity.builder()
                .name(campaignConjunction.getName())
                .description(campaignConjunction.getDescription())
                .build();
    }

}
