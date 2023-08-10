/* dks20165 created on 16.06.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response */

package com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignReward;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRewardDiscountResponse {
	private String discountType;
	private String discountValue;

	public static ProductRewardDiscountResponse fromModel(CampaignReward.CampaignRewardDiscount campaignReward){
		return ProductRewardDiscountResponse.builder()
				.discountType(campaignReward.getRewardDiscountType()!=null?
						campaignReward.getRewardDiscountType().getName()
						:null)
				.discountValue(campaignReward.getValue())
				.build();
	}
}
