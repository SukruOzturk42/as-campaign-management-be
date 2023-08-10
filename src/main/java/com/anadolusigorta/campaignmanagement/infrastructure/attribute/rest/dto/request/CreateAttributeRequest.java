package com.anadolusigorta.campaignmanagement.infrastructure.attribute.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.attribute.model.Attribute;
import com.anadolusigorta.campaignmanagement.domain.referencetype.model.DataType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAttributeRequest {

    private Long id;

    private String name;

    private String description;

    private String dataType;

    private Boolean isMat;


    public Attribute toModel(){
        return Attribute.builder()
                .id(id)
                .dataType(DataType.of(dataType))
                .description(description)
                .name(name)
                .isMat(isMat)
                .build();
    }
}
