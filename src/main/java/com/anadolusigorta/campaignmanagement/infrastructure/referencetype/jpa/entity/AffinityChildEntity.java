package com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.referencetype.model.AffinityChild;
import com.anadolusigorta.campaignmanagement.domain.referencetype.model.AffinityParent;
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
@Table(name = "affinity_child")
public class AffinityChildEntity extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "en_description")
    private String enDescription;

    @Column(name = "tr_description")
    private String trDescription;


    public AffinityChild toModel() {
        return AffinityChild.builder()
                .id(super.getId())
                .name(name)
                .description(description)
                .enDescription(enDescription)
                .trDescription(trDescription)
                .build();
    }
}
