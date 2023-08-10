package com.anadolusigorta.campaignmanagement.domain.saletask.model;

import java.util.stream.Stream;

public enum TaskStatus {

    DONE("done"),
    ON_PROGRESS("onProgress");

    private final String value;

    TaskStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TaskStatus of(String value) {
        return Stream.of(TaskStatus.values())
                .filter(status -> status.value.equals(value)).findFirst().orElseThrow();
    }

}
