package com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeKind;
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
@Table(name = "code_kind")
public class CodeKindEntity extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public CodeKind toModel() {
        return CodeKind.builder()
                .id(super.getId())
                .name(name)
                .description(description)
                .build();
    }

}
