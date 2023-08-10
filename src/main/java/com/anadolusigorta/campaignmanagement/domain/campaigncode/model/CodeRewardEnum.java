package com.anadolusigorta.campaignmanagement.domain.campaigncode.model;

import java.io.Serializable;
import java.util.stream.Stream;

public enum CodeRewardEnum implements Serializable {

    GIFT("GIFT"),
    DISCOUNT("DISCOUNT");

    private final String value;

    CodeRewardEnum(String value) { this.value = value; }

    public static CodeRewardEnum of(String value) {
        return Stream.of(CodeRewardEnum.values())
                .filter(reward -> reward.value.equals(value)).findFirst().orElseThrow();
    }

}
