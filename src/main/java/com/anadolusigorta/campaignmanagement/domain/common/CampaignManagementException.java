/* odeon_sukruo created on 26.04.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.common */

package com.anadolusigorta.campaignmanagement.domain.common;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampaignManagementException extends RuntimeException {
	private String key;
	private int errorCode;
	private final String[] args;

	public CampaignManagementException(String key) {
		super(key);
		this.key = key;
		args = new String[0];
	}

	public CampaignManagementException(String key,int errorCode) {
		super(key);
		this.key = key;
		args = new String[0];
		this.errorCode=errorCode;
	}

	public CampaignManagementException(String key, String... args) {
		super(key);
		this.key = key;
		this.args = args;
	}
}
