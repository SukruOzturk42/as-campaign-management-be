package com.anadolusigorta.campaignmanagement.domain.common.model;

import java.util.stream.Stream;

public enum NotificationDeliveryStatus {

    SENT("Gönderildi"),
    CANCELLED("İptal Edildi"),
    READ_DELIVERED("İletildi"),
    PENDING("Gönderim Bekliyor"),
    FAILED("Hata Alındı"),
    CM_FAILED("Kampus Hatası"),
    EMPTY("");

    private final String value;

    NotificationDeliveryStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static NotificationDeliveryStatus of(String value) {
        return Stream.of(NotificationDeliveryStatus.values())
                .filter(status -> status.value.equals(value)).findFirst().orElseThrow();
    }


}
