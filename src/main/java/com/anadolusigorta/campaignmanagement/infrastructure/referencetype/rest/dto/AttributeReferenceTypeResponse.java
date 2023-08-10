package com.anadolusigorta.campaignmanagement.infrastructure.referencetype.rest.dto;

import com.anadolusigorta.campaignmanagement.domain.referencetype.model.AttributeReferenceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttributeReferenceTypeResponse {

    private Long id;

    private String name;

    private String description;

    private AffinityInformationResponse affinityInformation;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AffinityInformationResponse{

        private Integer version;

        private AttributeResponse company;

        private AttributeResponse parent;

        private AttributeResponse child;

        private String descriptionTr;

        private String descriptionEn;

        private Boolean isSelectable;

        private Boolean isActive;

        private Boolean isIsBankCooperation;

        private Long extCode;

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AttributeResponse{


        private String name;

        private String description;



    }

    public static AttributeReferenceTypeResponse fromModel(AttributeReferenceType attributeReferenceType) {
        return AttributeReferenceTypeResponse.builder()
                .id(attributeReferenceType.getId())
                .name(attributeReferenceType.getName())
                .description(attributeReferenceType.getDescription())
                .affinityInformation(attributeReferenceType.getAffinityInformation() != null ?
                        fromModelToAffinityInformationResponse(attributeReferenceType) : null)
                .build();
    }

    public static List<AttributeReferenceTypeResponse> fromListOfModel(List<AttributeReferenceType> attributeReferenceTypeList) {
        return attributeReferenceTypeList.stream().map(AttributeReferenceTypeResponse::fromModel).collect(Collectors.toList());
    }

    public static AffinityInformationResponse fromModelToAffinityInformationResponse(AttributeReferenceType attributeReferenceType){
        return AffinityInformationResponse.builder()
                .company(AttributeResponse.builder()
                        .name(attributeReferenceType.getAffinityInformation().getAffinityCompany().getName())
                        .description(attributeReferenceType.getAffinityInformation().getAffinityCompany().getDescription())
                        .build())
                .parent(AttributeResponse.builder()
                        .name(attributeReferenceType.getAffinityInformation().getAffinityParent().getName())
                        .description(attributeReferenceType.getAffinityInformation().getAffinityParent().getDescription())
                        .build())
                .child(attributeReferenceType.getAffinityInformation().getAffinityChild()!=null?
                        AttributeResponse.builder()
                        .name(attributeReferenceType.getAffinityInformation().getAffinityChild().getName())
                        .description(attributeReferenceType.getAffinityInformation().getAffinityChild().getDescription())
                        .build():
                        null)
                .descriptionTr(attributeReferenceType.getAffinityInformation().getAffinityCompany().getDescription()+"-"+
                        attributeReferenceType.getAffinityInformation().getAffinityParent().getDescription()+"-"
                        +(attributeReferenceType.getAffinityInformation().getAffinityChild()!=null?
                        attributeReferenceType.getAffinityInformation().getAffinityChild().getDescription() :""))
                .descriptionEn(attributeReferenceType.getAffinityInformation().getAffinityCompany().getName()+"-"+
                        attributeReferenceType.getAffinityInformation().getAffinityParent().getName()+"-"
                        +(attributeReferenceType.getAffinityInformation().getAffinityChild()!=null?
                        attributeReferenceType.getAffinityInformation().getAffinityChild().getName() :""))
                .isSelectable(attributeReferenceType.getAffinityInformation().getIsSelectable())
                .isActive(attributeReferenceType.getAffinityInformation().getIsActive())
                .isIsBankCooperation(attributeReferenceType.getAffinityInformation().getIsIsBankCooperation())
                .version(attributeReferenceType.getAffinityInformation().getVersion())
                .extCode(attributeReferenceType.getAffinityInformation().getExtCode())
                .build();
    }
}
