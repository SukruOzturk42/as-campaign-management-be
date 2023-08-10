package com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.referencetype.model.AffinityInformation;
import com.anadolusigorta.campaignmanagement.domain.referencetype.model.AttributeReferenceType;
import com.anadolusigorta.campaignmanagement.infrastructure.attribute.jpa.entity.AttributeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "reference_type")
public class ReferenceTypeEntity extends AbstractEntity {

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", nullable = false, length = 400)
    private String description;

    @ManyToOne
    @JoinColumn(name = "attribute_id")
    private AttributeEntity attribute;

    @ManyToOne
    @JoinColumn(name = "affinity_information_id")
    private AffinityInformationEntity affinityInformation;

    @Column(name = "is_combined")
    private Boolean isCombined;

    public AttributeReferenceType toModel() {
        return AttributeReferenceType.builder()
                .id(super.getId())
                .name(name)
                .description((isCombined!=null &&
                        isCombined.equals(Boolean.TRUE))?
                        name+"-"+description:description)
                .affinityInformation(affinityInformation != null ? affinityInformation.toModel() : null)
                .build();
    }
}
