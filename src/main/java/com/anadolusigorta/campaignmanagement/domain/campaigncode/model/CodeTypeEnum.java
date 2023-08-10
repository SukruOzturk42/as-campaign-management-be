package com.anadolusigorta.campaignmanagement.domain.campaigncode.model;

import java.util.stream.Stream;

public enum CodeTypeEnum {
    THIRD_PARTY_CODE("THIRD_PARTY_CODE"),
    SINGLE_USE_CODE("SINGLE_USE_CODE"),
    UNLIMITED_USE_CODE("UNLIMITED_USE_CODE");

    private final String value;

    CodeTypeEnum(String value) {this.value = value;}

    public String getValue() {
        return value;
    }

    public static CodeTypeEnum of(String value) {
        return Stream.of(CodeTypeEnum.values())
                .filter(status -> status.value.equals(value)).findFirst().orElseThrow();
    }

}
