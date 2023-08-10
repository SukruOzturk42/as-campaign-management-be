package com.anadolusigorta.campaignmanagement.domain.saletransaction.model;

import java.util.stream.Stream;

public enum SaleTransactionOperationType {
	POLICY("POLICY"),
	PROPOSAL("PROPOSAL");

	private final String value;

	SaleTransactionOperationType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static SaleTransactionOperationType of(String value) {
		return Stream.of(SaleTransactionOperationType.values())
				.filter(status -> status.value.equals(value)).findFirst().orElseThrow();
	}
}
