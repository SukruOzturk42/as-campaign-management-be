package com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.referencetype.model.AffinityInformation;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "affinity_information")
public class AffinityInformationEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "affinity_company_id")
    private AffinityCompanyEntity affinityCompany;

    @ManyToOne
    @JoinColumn(name = "affinity_parent_id")
    private AffinityParentEntity affinityParent;

    @ManyToOne
    @JoinColumn(name = "affinity_child_id")
    private AffinityChildEntity affinityChild;

    @Column(name = "is_selectable")
    private Boolean isSelectable;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_is_bank_cooperation")
    private Boolean isIsBankCooperation;

    @Column(name = "ext_code")
    private Long extCode;

    @Version
    private Integer version;

    public AffinityInformation toModel() {
        return AffinityInformation.builder()
                .id(super.getId())
                .affinityCompany(affinityCompany != null ?  affinityCompany.toModel() : null)
                .affinityParent(affinityParent != null ?  affinityParent.toModel() : null)
                .affinityChild(affinityChild != null ?  affinityChild.toModel() : null)
                .isSelectable(isSelectable)
                .isActive(isActive)
                .isIsBankCooperation(isIsBankCooperation)
                .version(version)
                .extCode(extCode)
                .build();
    }
}
