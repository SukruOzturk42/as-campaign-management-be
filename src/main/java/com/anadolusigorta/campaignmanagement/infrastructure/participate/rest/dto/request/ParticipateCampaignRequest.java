/* odeon_sukruo created on 26.04.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.product.rest.dto */

package com.anadolusigorta.campaignmanagement.infrastructure.participate.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.participate.model.ParticipateCampaign;
import com.anadolusigorta.campaignmanagement.domain.contact.model.ContactCampaignInformation;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipateCampaignRequest {
	@NotNull

	private String contactNumber;

	@NotNull

	private String transactionId;

	@NotNull

	private LocalDateTime date;

	@NotNull
	private Long campaignId;

	@NotNull
	private Long ruleGroupId;

	@NotNull
	private Long campaignVersion;

	@NotNull
	private ParametersRequest parameters;

	@Setter
	@Getter
	public static class ParametersRequest {
		@NotNull
		private Map<String,Object> parametersInfo;
		private List<Map<String,String>> subProducts;
	}

	public ParticipateCampaign toModel(){
		return ParticipateCampaign.builder()
				.contactNumber(contactNumber)
				.campaignId(campaignId)
				.campaignVersion(campaignVersion)
				.ruleGroupId(ruleGroupId)
				.parameters(ContactCampaignInformation.builder()
								.parametersInfo(parameters.getParametersInfo())
								.subProducts(parameters.subProducts)
								.build()).build();
	}


}
