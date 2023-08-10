package com.anadolusigorta.campaignmanagement.domain.referencetype.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AffinityChild {

    private Long id;

    private String name;

    private String description;

    private String enDescription;

    private String trDescription;

}
