package com.anadolusigorta.campaignmanagement.infrastructure.referencetype.rest.dto;

import com.anadolusigorta.campaignmanagement.domain.referencetype.model.AttributeReferenceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUpdateReferenceTypeRequest {

    private Long id;

    private String name;

    private String description;

    public AttributeReferenceType toModel(){
        return AttributeReferenceType.builder()
                .id(id)
                .name(name)
                .description(description)
                .build();
    }
}
