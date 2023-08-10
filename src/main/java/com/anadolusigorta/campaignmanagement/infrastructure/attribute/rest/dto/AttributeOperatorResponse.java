package com.anadolusigorta.campaignmanagement.infrastructure.attribute.rest.dto;

import com.anadolusigorta.campaignmanagement.domain.operator.AttributeOperator;
import com.anadolusigorta.campaignmanagement.domain.operator.OperatorEnum;
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
public class AttributeOperatorResponse {

    private Long id;

    private String name;

    private OperatorEnum operatorEnum;

    public static AttributeOperatorResponse fromModel(AttributeOperator attributeOperator) {
        return AttributeOperatorResponse.builder()
                .id(attributeOperator.getId())
                .name(attributeOperator.getName())
                .operatorEnum(attributeOperator.getOperatorEnum())
                .build();
    }

    public static List<AttributeOperatorResponse> fromListOfModel(List<AttributeOperator> attributeOperatorList) {
        return attributeOperatorList.stream().map(AttributeOperatorResponse::fromModel).collect(Collectors.toList());
    }
}
