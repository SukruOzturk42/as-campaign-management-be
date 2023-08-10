/* odeon_sukruo created on 3.05.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.campaign.model */

package com.anadolusigorta.campaignmanagement.domain.participate.model;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignInformation;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.DiscountCode;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.GiftCode;
import com.anadolusigorta.campaignmanagement.domain.reward.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipatedCustomerCampaign {
	private Long id;
	private String customerNumber;
	private Integer orderOfParticipation;
	private Integer numberOfParticipation;
	private CampaignInformation campaignInformation;
	private CampaignReward campaignReward;
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class CampaignReward {

		private Long id;

		private CampaignRewardDiscount campaignRewardDiscount;

		private CampaignRewardGift campaignRewardGift;

		@Data
		@Builder
		@AllArgsConstructor
		@NoArgsConstructor
		public static class CampaignRewardDiscount {

			private Long id;

			private String value;

			private String name;

			private DiscountCode discountCode;

			private RewardDiscountKind rewardDiscountType;

		}

		@Data
		@Builder
		@AllArgsConstructor
		@NoArgsConstructor
		public static class CampaignRewardGift {

			private Long id;

			private String rewardType;

			private String name;

			private GiftCode giftCode;

			private RewardGiftType rewardGiftType;

		}

		}

}
