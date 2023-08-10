package com.anadolusigorta.campaignmanagement.domain.campaign.model;

import java.util.stream.Stream;

public enum CampaignCreateModeType {

    INITIAL_CREATE("INITIAL_CREATE"),
    NEW_VERSION("NEW_VERSION"),
    COPY_CAMPAIGN("COPY_CAMPAIGN");

    private final String value;

    CampaignCreateModeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CampaignCreateModeType of(String value) {
        return Stream.of(CampaignCreateModeType.values())
                .filter(status -> status.value.equals(value)).findFirst().orElseThrow();
    }

}
