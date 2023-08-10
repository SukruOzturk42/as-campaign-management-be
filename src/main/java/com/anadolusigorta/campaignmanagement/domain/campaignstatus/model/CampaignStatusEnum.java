package com.anadolusigorta.campaignmanagement.domain.campaignstatus.model;

import java.io.Serializable;
import java.util.stream.Stream;

public enum CampaignStatusEnum implements Serializable {
    ACTIVE_CAMPAIGN("ACTIVE_CAMPAIGN"),
    REVISED_CAMPAIGN("REVISED_CAMPAIGN"),
    CLOSED_CAMPAIGN("CLOSED_CAMPAIGN"),
    TEMPLATE_CAMPAIGN("TEMPLATE_CAMPAIGN"),
    PENDING_CAMPAIGN("PENDING_CAMPAIGN"),
    PENDING_PUBLISH("PENDING_PUBLISH"),
    PENDING_VERSION_START_DATE("PENDING_VERSION_START_DATE");

    private final String value;

    CampaignStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CampaignStatusEnum of(String value) {
        return Stream.of(CampaignStatusEnum.values())
                .filter(status -> status.value.equals(value)).findFirst().orElseThrow();
    }
}
