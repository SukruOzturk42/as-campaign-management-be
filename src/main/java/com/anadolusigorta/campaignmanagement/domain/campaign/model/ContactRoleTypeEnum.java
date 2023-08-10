package com.anadolusigorta.campaignmanagement.domain.campaign.model;

import java.util.stream.Stream;

public enum ContactRoleTypeEnum {

    INSURER("6"),
    INSURED("1");

    private final String value;

    ContactRoleTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ContactRoleTypeEnum of(String value) {
        return Stream.of(ContactRoleTypeEnum.values())
                .filter(status -> status.value.equals(value))
                .findFirst()
                .orElseThrow();
    }

}
