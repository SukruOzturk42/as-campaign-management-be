package com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeType;
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
@Table(name = "code_type")
public class CodeTypeEntity extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public CodeType toModel() {
        return CodeType.builder()
                .id(super.getId())
                .name(name)
                .description(description)
                .build();
    }

}
