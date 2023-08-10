package com.anadolusigorta.campaignmanagement.infrastructure.attribute.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.attribute.model.AttributeType;
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
public class AttributeTypeResponse {

    private Long id;

    private String name;

    private String description;

    public static AttributeTypeResponse fromModel(AttributeType attributeType) {
        return AttributeTypeResponse.builder()
                .id(attributeType.getId())
                .name(attributeType.getName())
                .description(attributeType.getDescription())
                .build();
    }

    public static List<AttributeTypeResponse> fromListOfModel(List<AttributeType> attributeTypeList) {
        return attributeTypeList.stream().map(AttributeTypeResponse::fromModel).collect(Collectors.toList());
    }
}
