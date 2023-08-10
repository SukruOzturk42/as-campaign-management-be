package com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.referencetype.model.AffinityCompany;
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
@Table(name = "affinity_company")
public class AffinityCompanyEntity extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "isMilitary")
    private Boolean isMilitary;


    public AffinityCompany toModel() {
        return AffinityCompany.builder()
                .id(super.getId())
                .name(name)
                .description(description)
                .isMilitary(isMilitary)
                .build();
    }
}
