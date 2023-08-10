package com.anadolusigorta.campaignmanagement.domain.action.model;

import java.util.stream.Stream;

public enum ActionTypeEnum {

    ACTIVE_CAMPAIGN("ACTIVATE_CAMPAIGN"),
    PUBLISH("PUBLISH"),
    CLOSE_CAMPAIGN("CLOSE_CAMPAIGN"),
    PARAMETER("PARAMETER"),
    COPY("COPY"),
    REJECT("REJECT"),
    SEND_FOR_APPROVAL("SEND_FOR_APPROVAL"),
    EDIT("EDIT"),
    PUT_CAMPAIGN_ON_HOLD("PUT_CAMPAIGN_ON_HOLD");

    private final String value;

    ActionTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ActionTypeEnum of(String value) {
        return Stream.of(ActionTypeEnum.values())
                .filter(status -> status.value.equals(value)).findFirst().orElseThrow();
    }

}
