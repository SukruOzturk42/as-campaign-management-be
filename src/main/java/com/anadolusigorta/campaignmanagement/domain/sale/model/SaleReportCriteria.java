/* odeon_sukruo created on 26.04.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.product.rest.dto */

package com.anadolusigorta.campaignmanagement.domain.sale.model;


import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionOperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleReportCriteria {

	@NotNull
	private Long campaignId;


	private String contactNumber;


	private String policyNumber;

	private String proposalNumber;

	private String requestType;

	private Long campaignVersion;

}
