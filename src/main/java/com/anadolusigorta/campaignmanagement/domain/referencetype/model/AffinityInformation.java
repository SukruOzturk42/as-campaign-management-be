package com.anadolusigorta.campaignmanagement.domain.referencetype.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AffinityInformation {

    private Long id;

    private Boolean isSelectable;

    private Boolean isActive;

    private Boolean isIsBankCooperation;

    private AffinityCompany affinityCompany;

    private AffinityParent affinityParent;

    private AffinityChild affinityChild;

    private Integer version;

    private Long extCode;

}
