package com.anadolusigorta.campaignmanagement.domain.campaigncode.model;

import java.io.Serializable;
import java.util.stream.Stream;

public enum CodeStatusEnum implements Serializable {
    UNUSED("UNUSED"),
    PROPOSED("PROPOSED"),
    USED("USED");

    private final String value;

    CodeStatusEnum(String value) {this.value = value;}

    public static CodeStatusEnum of(String value) {
        return Stream.of(CodeStatusEnum.values())
                .filter(status -> status.value.equals(value)).findFirst().orElseThrow();
    }

}
