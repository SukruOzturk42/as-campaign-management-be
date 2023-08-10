package com.anadolusigorta.campaignmanagement.domain.campaigncode.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountCode implements Serializable {

    private String code;

    private Boolean isActive;

    private String contactNumber;

    private CodeStatusEnum codeStatusEnum;

    private DiscountCodeInformation discountCodeInformation;

}
