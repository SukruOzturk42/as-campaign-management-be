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
public class GiftCode implements Serializable {

    private Long id;

    private String code;

    private String contactNumber;

    private CodeStatusEnum codeStatusEnum;

    private GiftCodeInformation giftCodeInformation;

    private String qrCodeUrl;

}
