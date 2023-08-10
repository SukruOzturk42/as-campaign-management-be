package com.anadolusigorta.campaignmanagement.domain.campaigncode.model;

import java.util.stream.Stream;

public enum SaleCodeTypeEnum {
    TCKN("TCKN"),
    YKN("YKN"),
    VKN("VKN"),
    CODE("CODE");

    private final String value;

    SaleCodeTypeEnum(String value) {this.value = value;}

    public String getValue() {
        return value;
    }

    public static SaleCodeTypeEnum of(String value) {
        return Stream.of(SaleCodeTypeEnum.values())
                .filter(status -> status.value.equals(value)).findFirst().orElseThrow();
    }

}
