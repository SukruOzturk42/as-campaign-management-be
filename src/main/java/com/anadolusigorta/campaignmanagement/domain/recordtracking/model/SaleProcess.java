package com.anadolusigorta.campaignmanagement.domain.recordtracking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleProcess {

    private Long id;

    private String name;

    private String description;

}
