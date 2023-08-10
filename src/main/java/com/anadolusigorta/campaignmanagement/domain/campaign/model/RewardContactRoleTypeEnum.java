package com.anadolusigorta.campaignmanagement.domain.campaign.model;

import java.util.stream.Stream;

public enum RewardContactRoleTypeEnum {

    INSURER("insurer"),
    INSURED("insured");

    private final String value;

    RewardContactRoleTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RewardContactRoleTypeEnum of(String value) {
        return Stream.of(RewardContactRoleTypeEnum.values())
                .filter(status -> status.value.equals(value))
                .findFirst()
                .orElseThrow();
    }

}
