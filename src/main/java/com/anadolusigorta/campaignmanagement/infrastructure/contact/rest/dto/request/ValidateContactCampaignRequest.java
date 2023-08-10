/* odeon_sukruo created on 26.04.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.product.rest.dto */

package com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.contact.model.ContactCampaignInformation;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class ValidateContactCampaignRequest {
	@NotNull
	private String contactNumber;

	@NotNull
	@NotNull
	private Long campaignId;

	@NotNull
	private Long ruleGroupId;

	@NotNull
	private Long campaignVersion;

	@NotNull
	private Map<String,Object> parametersInfo;


	public ContactCampaignInformation toModel(){
		log.info(String.format("Validate contact campaign request :%s",this));
		return ContactCampaignInformation.builder()
				.parametersInfo(parametersInfo)
				.campaignId(campaignId)
				.campaignVersion(campaignVersion)
				.ruleGroupId(ruleGroupId)
				.contactNumber(contactNumber)
				.build();
	}

}
