package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignType;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.company.jpa.entity.CompanyEntity;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "campaign_type")
public class CampaignTypeEntity extends AbstractEntity {

    @Column(name = "name", nullable = false, length = 50, unique = true)
    private String name;

    @Column(name = "description", nullable = false, length = 400)
    private String description;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;

    public CampaignType toModel(){
        return CampaignType.builder()
                .id(super.getId())
                .name(name)
                .description(description)
                .build();
    }

    public static CampaignTypeEntity fromModel(CampaignType campaignType) {
        return CampaignTypeEntity.builder()
                .name(campaignType.getName())
                .description(campaignType.getDescription())
                .build();
    }

}
