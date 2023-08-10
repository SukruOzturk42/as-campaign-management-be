package com.anadolusigorta.campaignmanagement.infrastructure.referencetype.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAffinityReferenceTypeRequest {

    private Long id;

    @NotNull
    private Long companyId;

    @NotNull
    private Long parentId;

    private Long childId;

    private Boolean isSelectable = false;

    private Boolean isActive = false;

    private Boolean isIsBankCooperation = false;

}
