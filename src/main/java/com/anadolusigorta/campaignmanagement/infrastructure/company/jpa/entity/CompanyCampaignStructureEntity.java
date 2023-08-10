package com.anadolusigorta.campaignmanagement.infrastructure.company.jpa.entity;


import com.anadolusigorta.campaignmanagement.domain.company.model.CompanyCampaignStructure;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "company_campaign_structure")
public class CompanyCampaignStructureEntity extends AbstractEntity {

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", nullable = false, length = 400)
    private String description;

    @Column(name = "reward_name", nullable = false)
    private String rewardName;

    @Column(name = "reward_description", nullable = false)
    private String rewardDescription;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;

    @ManyToOne(fetch = FetchType.LAZY)
    private CompanyCampaignStructureEntity parent;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "parent")
    private Set<CompanyCampaignStructureEntity> children;

    public CompanyCampaignStructure toModel(){
        return CompanyCampaignStructure.builder()
                .id(super.getId())
                .name(name)
                .description(description)
                .rewardName(rewardName)
                .rewardDescription(rewardDescription)
                .children(this.getChildren().stream()
                        .map(CompanyCampaignStructureEntity::toModel).collect(Collectors.toList())).build();
    }
}
