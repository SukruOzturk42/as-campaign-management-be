package com.anadolusigorta.campaignmanagement.domain.saletransaction.model;

import java.util.stream.Stream;

public enum SaleTransactionType {
	FIND_CAMPAIGN("FIND_CAMPAIGN"),
	CHECK_CODE("CHECK_CODE"),
	NOTIFY("NOTIFY");

	private final String value;

	SaleTransactionType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static SaleTransactionType of(String value) {
		return Stream.of(SaleTransactionType.values())
				.filter(status -> status.value.equals(value)).findFirst().orElseThrow();
	}
}
