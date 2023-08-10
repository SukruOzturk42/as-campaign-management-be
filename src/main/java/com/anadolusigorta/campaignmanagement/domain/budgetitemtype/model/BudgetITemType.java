package com.anadolusigorta.campaignmanagement.domain.budgetitemtype.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BudgetITemType {

    private Long id;

    private String name;

    private String description;
}
