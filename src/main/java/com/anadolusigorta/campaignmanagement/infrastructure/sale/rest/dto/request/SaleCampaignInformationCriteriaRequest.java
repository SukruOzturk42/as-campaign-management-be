/* odeon_sukruo created on 26.04.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.product.rest.dto */

package com.anadolusigorta.campaignmanagement.infrastructure.sale.rest.dto.request;


import com.anadolusigorta.campaignmanagement.domain.sale.model.SaleReportCriteria;
import lombok.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleCampaignInformationCriteriaRequest {

	@NotNull
	private Long campaignId;


	private String contactNumber;

	private String policyNumber;

	private String proposalNumber;

	private String requestType;

	private Long campaignVersion;

	public SaleReportCriteria toModel(){
		return SaleReportCriteria.builder()
				.campaignId(campaignId)
				.campaignVersion(campaignVersion)
				.requestType(requestType)
				.contactNumber(contactNumber)
				.policyNumber(policyNumber)
				.proposalNumber(proposalNumber)
				.build();
	}


}
