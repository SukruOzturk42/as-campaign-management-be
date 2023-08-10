package com.anadolusigorta.campaignmanagement.domain.campaignstatus.model;

import java.util.stream.Stream;

public enum CampaignSearchStatusEnum {
    ACTIVE_CAMPAIGNS("Aktif Kampanyalar"),
    TERMINATED_CAMPAIGNS("Biten Kampanyalar"),
    FUTURE_CAMPAIGNS("İleri Tarihli Kampanyalar"),
    PENDING_CAMPAIGNS("Beklemeye Alınan Kampanyalar"),
    EMPTY("");

    private final String value;

    CampaignSearchStatusEnum(String value) { this.value = value; }

    public String getValue() { return value; }

    public static CampaignSearchStatusEnum of(String value) {
        return Stream.of(CampaignSearchStatusEnum.values())
                .filter(status -> status.value.equals(value)).findFirst().orElseThrow();
    }
}
