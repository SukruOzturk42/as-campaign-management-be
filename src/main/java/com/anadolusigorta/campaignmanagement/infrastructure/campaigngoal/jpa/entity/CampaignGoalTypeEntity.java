package com.anadolusigorta.campaignmanagement.infrastructure.campaigngoal.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaigngoal.model.CampaignGoalType;
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
@Table(name = "campaign_goal_type")
public class CampaignGoalTypeEntity extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public CampaignGoalType toModel() {
        return CampaignGoalType.builder()
                .id(super.getId())
                .name(name)
                .description(description)
                .build();
    }

}
