package com.anadolusigorta.campaignmanagement.domain.operator;

import java.io.Serializable;
import java.util.stream.Stream;

public enum ConjunctionOperatorEnum implements Serializable {
	AND("AND"),
	OR("OR");

	private final String value;

	ConjunctionOperatorEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static ConjunctionOperatorEnum of(String value) {
		return Stream.of(ConjunctionOperatorEnum.values())
				.filter(status -> status.value.equals(value)).findFirst().orElseThrow();
	}
}
