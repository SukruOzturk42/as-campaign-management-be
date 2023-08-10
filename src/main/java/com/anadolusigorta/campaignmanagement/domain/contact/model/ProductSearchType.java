package com.anadolusigorta.campaignmanagement.domain.contact.model;

import java.util.stream.Stream;

public enum ProductSearchType {

    PRODUCT_CAMPAIGN("PRODUCT_CAMPAIGN"),
    CAMPAIGN("CAMPAIGN");

    private final String value;

    ProductSearchType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ProductSearchType of(String value) {
        return Stream.of(ProductSearchType.values())
                .filter(status -> status.value.equals(value)).findFirst().orElseThrow();
    }

}
