package com.anadolusigorta.campaignmanagement.infrastructure.referencetype.rest.dto;

import com.anadolusigorta.campaignmanagement.domain.referencetype.model.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AffinityInformationResponse {

    private Long id;

    private String name;

    private String description;

    private String descriptionEn;

    @NotNull
    private Long companyId;

    @NotNull
    private Long parentId;

    private Long childId;

    private Boolean isSelectable = false;

    private Boolean isActive = false;

    private Boolean isIsBankCooperation = false;

    private Long extCode;




    public static AffinityInformationResponse fromModel(AttributeReferenceType attributeReferenceType) {
        return AffinityInformationResponse.builder()
                .id(attributeReferenceType.getId())
                .name(attributeReferenceType.getName())
                .description(attributeReferenceType.getDescription())
                .descriptionEn(attributeReferenceType.getDescriptionEn())
                .companyId(attributeReferenceType.getAffinityInformation() != null
                        ? attributeReferenceType.getAffinityInformation().getAffinityCompany().getId() :
                        null)
                .parentId(attributeReferenceType.getAffinityInformation() != null ?
                        attributeReferenceType.getAffinityInformation().getAffinityParent().getId():
                        null)
                .childId(attributeReferenceType.getAffinityInformation() != null && attributeReferenceType.getAffinityInformation().getAffinityChild() != null ?
                        attributeReferenceType.getAffinityInformation().getAffinityChild().getId() : null)
                .isSelectable(attributeReferenceType.getAffinityInformation() != null ?
                    attributeReferenceType.getAffinityInformation().getIsSelectable() :
                    null)
                .isActive(attributeReferenceType.getAffinityInformation() != null ?
                        attributeReferenceType.getAffinityInformation().getIsActive() :
                        null)
                .isIsBankCooperation(attributeReferenceType.getAffinityInformation() != null ?
                        attributeReferenceType.getAffinityInformation().getIsIsBankCooperation() :
                        null)
                .extCode(attributeReferenceType.getAffinityInformation() != null ?
                        attributeReferenceType.getAffinityInformation().getExtCode() :
                        null)
                .build();
    }

    public static List<AffinityInformationResponse> fromListOfModel(List<AttributeReferenceType> affinityInformations) {
        return affinityInformations
                .stream()
                .map(AffinityInformationResponse::fromModel)
                .collect(Collectors.toList());
    }
}
