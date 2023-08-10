package com.anadolusigorta.campaignmanagement.domain.campaignstatus.model;

import java.util.stream.Stream;

public enum CampaignStatusTypeEnum {
    STATUS_TYPE("STATUS_TYPE"),
    APPROVAL_STATUS_TYPE("APPROVAL_STATUS_TYPE");

    private final String value;

    CampaignStatusTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CampaignStatusTypeEnum of(String value) {
        return Stream.of(CampaignStatusTypeEnum.values())
                .filter(statusType -> statusType.value.equals(value)).findFirst().orElseThrow();
    }
}
