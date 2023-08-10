package com.anadolusigorta.campaignmanagement.infrastructure.common.enums;

import java.util.stream.Stream;

public enum UploadFileEnum {

    UPLOADED("Yüklendi"),
    UPLOADING("Yükleniyor"),
    NOT_UPLOADED("Yüklenemedi");

    private final String value;

    UploadFileEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static UploadFileEnum of(String value) {
        return Stream.of(UploadFileEnum.values())
                .filter(status -> status.value.equals(value)).findFirst().orElseThrow();
    }

}
