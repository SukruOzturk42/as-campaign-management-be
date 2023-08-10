package com.anadolusigorta.campaignmanagement.domain.attribute.model;

import com.anadolusigorta.campaignmanagement.domain.referencetype.model.DataType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attribute {

    private Long id;

    private String name;

    private String description;

    private DataType dataType;

    private String campaignType;

    private Boolean isMat;

    private Boolean isReward;

    private String tableName;

}
