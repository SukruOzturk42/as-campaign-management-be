package com.anadolusigorta.campaignmanagement.infrastructure.login.rest.dto.request;

import lombok.Getter;

import java.io.Serializable;
@Getter
public class SingleSignOnRequest implements Serializable {
	private Boolean withoutToolbars;

	private String withoutIsam;
}
