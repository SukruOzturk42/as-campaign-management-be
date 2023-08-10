package com.anadolusigorta.campaignmanagement.domain.referencetype.model;
import java.util.stream.Stream;

public enum DataType {
	SELECT("SELECT"),
	DATETIME("DATETIME"),
	RADIO("RADIO"),
	INPUT("INPUT"),
	INPUT_DECIMAL("INPUT_DECIMAL"),
	DATE("DATE");

	private final String value;

	DataType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static DataType of(String value) {
		return Stream.of(DataType.values())
				.filter(status -> status.value.equals(value)).findFirst().orElseThrow();
	}
}
