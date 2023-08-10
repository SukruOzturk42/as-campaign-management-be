package com.anadolusigorta.campaignmanagement.infrastructure.attribute.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.attribute.model.Attribute;
import com.anadolusigorta.campaignmanagement.domain.referencetype.model.DataType;
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
public class AttributeResponse {

    private Long id;

    private String name;

    private String description;

    private DataType dataType;

    private String campaignType;


    private Boolean isMat;

    public static AttributeResponse fromModel(Attribute attribute) {
        return AttributeResponse.builder()
                .id(attribute.getId())
                .name(attribute.getName())
                .description(attribute.getDescription())
                .dataType(attribute.getDataType())
                .campaignType(attribute.getCampaignType())
                .isMat(attribute.getIsMat())
                .build();
    }

    public static List<AttributeResponse> fromListOfModel(List<Attribute> attributeList) {
        return attributeList.stream().map(AttributeResponse::fromModel).collect(Collectors.toList());
    }

}
