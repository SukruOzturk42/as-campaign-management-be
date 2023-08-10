package com.anadolusigorta.campaignmanagement.domain.attribute.model;

import java.util.stream.Stream;

public enum AttributeTypeEnum {

    RULE("RULE"),
    PARAMETER("PARAMETER");

    private final String value;

    AttributeTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static AttributeTypeEnum of(String value) {
        return Stream.of(AttributeTypeEnum.values())
                .filter(status -> status.value.equals(value)).findFirst().orElseThrow();
    }

}
