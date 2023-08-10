package com.anadolusigorta.campaignmanagement.infrastructure.attribute.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.attribute.model.Attribute;
import com.anadolusigorta.campaignmanagement.domain.referencetype.model.DataType;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.entity.ReferenceTypeEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "attribute")
public class AttributeEntity extends AbstractEntity {

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", nullable = false, length = 400)
    private String description;

    @ManyToOne
    @JoinColumn(name = "attribute_type_id", nullable = false)
    private AttributeTypeEntity attributeType;


    @Enumerated(EnumType.STRING)
    private DataType dataType;

    @OneToMany(mappedBy = "attribute")
    private Set<ReferenceTypeEntity> referenceTypes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "campaign_type_id", nullable = false)
    private CampaignTypeEntity campaignType;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name="is_mat")
    private Boolean isMat;

    @Column(name="is_reward")
    private Boolean isReward = false;

    @Column(name = "is_active")
    private Boolean isActive = true;


    public Attribute toModel() {
        return Attribute.builder()
                .id(super.getId())
                .name(name)
                .dataType(dataType)
                .description(description)
                .campaignType(campaignType.getDescription())
                .isMat(isMat)
                .isReward(isReward)
                .build();
    }
}
