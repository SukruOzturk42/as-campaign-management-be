package com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model;

import java.util.stream.Stream;

public enum CustomerExcelSaveStatus {


    RECORDED("Müşteri Listesi Kaydedildi"),
    BEING_RECORDED("Kod Dağıtma Devam Ediyor"),
    FAILED("Dosya Eklenirken Hata Alındı");

    private final String value;

    CustomerExcelSaveStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CustomerExcelSaveStatus of(String value) {
        return Stream.of(CustomerExcelSaveStatus.values())
                .filter(status -> status.value.equals(value)).findFirst().orElseThrow();
    }
}
