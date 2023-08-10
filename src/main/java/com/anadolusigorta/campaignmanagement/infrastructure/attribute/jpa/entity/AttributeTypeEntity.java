package com.anadolusigorta.campaignmanagement.infrastructure.attribute.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.attribute.model.AttributeType;
import com.anadolusigorta.campaignmanagement.domain.attribute.model.AttributeTypeEnum;
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
@Table(name = "attribute_type")
public class AttributeTypeEntity extends AbstractEntity {

    @Column(name = "name", nullable = false, length = 50, unique = true)
    private String name;

    @Column(name = "description", nullable = false, length = 400)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private AttributeTypeEnum type;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;

    public AttributeType toModel() {
        return AttributeType.builder()
                .id(super.getId())
                .name(name)
                .description(description)
                .type(type)
                .build();
    }

    public static AttributeTypeEntity fromModel(AttributeType attributeType) {
        return AttributeTypeEntity.builder()
                .name(attributeType.getName())
                .description(attributeType.getDescription())
                .type(attributeType.getType())
                .build();
    }

}
