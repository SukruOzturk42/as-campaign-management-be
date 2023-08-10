package com.anadolusigorta.campaignmanagement.domain.saletask.model;

import java.util.stream.Stream;

public enum TaskState {

    DONE("done"),
    ON_PROGRESS("onProgress");

    private final String value;

    TaskState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TaskState of(String value) {
        return Stream.of(TaskState.values())
                .filter(status -> status.value.equals(value)).findFirst().orElseThrow();
    }

}
