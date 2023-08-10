package com.anadolusigorta.campaignmanagement.domain.campaigncode.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountCodeInformationCriteria {

    private Long id;

    private String codeGroupName;

    private Long codeTypeId;

}
