package com.anadolusigorta.campaignmanagement.domain.operator;

import java.io.Serializable;
import java.util.stream.Stream;

public enum OperatorDataTypeEnum implements Serializable {
	COLLECTION("COLLECTION"),
	STRING("STRING"),
	DATE("DATE");

	private final String value;

	OperatorDataTypeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static OperatorDataTypeEnum of(String value) {
		return Stream.of(OperatorDataTypeEnum.values())
				.filter(status -> status.value.equals(value)).findFirst().orElseThrow();
	}
}
