package com.anadolusigorta.campaignmanagement.domain.saletransaction.model;

import java.util.stream.Stream;

public enum SaleTransactionCheckOperationType {
	CODE_CHECK("CODE_CHECK"),
	SAVE_POLICY("SAVE_POLICY");

	private final String value;

	SaleTransactionCheckOperationType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static SaleTransactionCheckOperationType of(String value) {
		return Stream.of(SaleTransactionCheckOperationType.values())
				.filter(status -> status.value.equals(value)).findFirst().orElseThrow();
	}
}
