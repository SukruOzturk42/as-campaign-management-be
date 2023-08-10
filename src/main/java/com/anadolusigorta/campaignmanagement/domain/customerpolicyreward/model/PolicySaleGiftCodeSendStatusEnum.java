package com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model;

import com.anadolusigorta.campaignmanagement.domain.attribute.model.AttributeTypeEnum;

import java.util.stream.Stream;

public enum PolicySaleGiftCodeSendStatusEnum {

    PENDING("Gönderim bekleniyor"),
    SUCCESS("Gönderildi"),
    FAILED("Hata Alındı"),
    INSUFFICIENT("Kod Atanamadı");

    private final String value;

    PolicySaleGiftCodeSendStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PolicySaleGiftCodeSendStatusEnum of(String value) {
        return Stream.of(PolicySaleGiftCodeSendStatusEnum.values())
                .filter(status -> status.value.equals(value)).findFirst().orElseThrow();
    }
}
