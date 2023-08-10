package com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.sale.model.RemoveSaleReward;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;

@Data
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RemoveSaleCampaignResponse {

	@NotNull
	private String contactNumber;


	@NotNull
	private String policyNumber;

	@NotNull
	private Long campaignId;

	@NotNull
	private Long ruleGroupId;



	public static RemoveSaleCampaignResponse fromModel(RemoveSaleReward removeSaleReward) {

		return RemoveSaleCampaignResponse.builder()
				.contactNumber(removeSaleReward.getContactNumber())
				.policyNumber(removeSaleReward.getPolicyNumber())
				.campaignId(removeSaleReward.getCampaignId())
				.ruleGroupId(removeSaleReward.getRuleGroupId())
				.build();
	}

}
