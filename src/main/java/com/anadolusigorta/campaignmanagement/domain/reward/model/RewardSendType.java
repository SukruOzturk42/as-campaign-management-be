package com.anadolusigorta.campaignmanagement.domain.reward.model;

import java.util.stream.Stream;

public enum RewardSendType {

    SMS("sms"),
    MAIL("mail");

    private final String value;

    RewardSendType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RewardSendType of(String value) {
        return Stream.of(RewardSendType.values())
                .filter(status -> status.value.equals(value)).findFirst().orElseThrow();
    }

}
