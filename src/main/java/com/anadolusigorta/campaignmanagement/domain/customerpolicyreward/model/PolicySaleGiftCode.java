package com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model;

import java.io.Serializable;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicySaleGiftCode implements Serializable {

    private Long id;

    private String code;

    private Boolean isActive;

    private String contactNumber;

    private CodeStatusEnum codeStatusEnum;

    private PolicySaleGiftCodeInformation giftCodeInformation;

    private String qrCodeUrl;

}
