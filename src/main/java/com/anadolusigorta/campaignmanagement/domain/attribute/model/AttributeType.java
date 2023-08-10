package com.anadolusigorta.campaignmanagement.domain.attribute.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttributeType {

    private Long id;

    private String name;

    private String description;

    private AttributeTypeEnum type;

}
