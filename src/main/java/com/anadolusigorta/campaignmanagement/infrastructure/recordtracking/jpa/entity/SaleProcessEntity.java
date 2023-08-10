package com.anadolusigorta.campaignmanagement.infrastructure.recordtracking.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.recordtracking.model.SaleProcess;
import com.anadolusigorta.campaignmanagement.domain.referencetype.model.AffinityChild;
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
public class SaleProcessEntity extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;


    public SaleProcess toModel() {
        return SaleProcess.builder()
                .id(super.getId())
                .name(name)
                .description(description)
                .build();
    }
}
