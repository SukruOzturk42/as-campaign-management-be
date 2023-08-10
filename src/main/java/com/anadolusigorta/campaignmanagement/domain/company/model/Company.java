package com.anadolusigorta.campaignmanagement.domain.company.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    private Long id;

    private String name;

    private String description;

    private Boolean isMilitary;


}
