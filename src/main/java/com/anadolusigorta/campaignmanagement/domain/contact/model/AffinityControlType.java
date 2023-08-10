package com.anadolusigorta.campaignmanagement.domain.contact.model;

import java.util.stream.Stream;

public enum AffinityControlType {

    ALL("ALL"),
    CAMPAIGN("CAMPAIGN"),
    AFFINITY("AFFINITY");

    private final String value;

    AffinityControlType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static AffinityControlType of(String value) {
        return Stream.of(AffinityControlType.values())
                .filter(status -> status.value.equals(value)).findFirst().orElseThrow();
    }

}
