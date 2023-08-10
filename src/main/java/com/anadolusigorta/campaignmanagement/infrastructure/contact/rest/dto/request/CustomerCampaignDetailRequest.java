/* odeon_sukruo created on 26.04.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.product.rest.dto */

package com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.request;


import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCampaignDetailRequest {


	private Long campaignId;

	private Long campaignVersion;

	private Long ruleGroupId;



}
