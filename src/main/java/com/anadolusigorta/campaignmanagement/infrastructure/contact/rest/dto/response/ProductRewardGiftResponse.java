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
public class ProductRewardGiftResponse {
	private String rewardType;
	private String rewardName;
	private Long rewardCount;

	public static ProductRewardGiftResponse fromModel(CampaignReward.CampaignRewardGift campaignRewardGift){
		return ProductRewardGiftResponse.builder()
				.rewardType(campaignRewardGift.getRewardGiftType().getName())
				.rewardName(campaignRewardGift.getRewardGiftProduct()!=null?campaignRewardGift.getRewardGiftProduct().getName()
						:campaignRewardGift.getGiftCodeInformation().getRewardGiftTicketType().getName())
				.rewardCount(campaignRewardGift.getTotalProductCount())
				.build();
	}


}
