package com.anadolusigorta.campaignmanagement.domain.sale.model;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignReward;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.GiftCode;
import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardNotificationStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SaleRewardGift {

    private Long id;
    private CampaignReward campaignRewardGift;
    private SaleInformation saleInformation;
    private LocalDateTime rewardSendDate;
    private GiftCode giftCode;
    private List<GiftCode> giftCodes;
    private RewardNotificationStatus rewardNotificationStatus;
    private String notificationDeliveryDescription;
    private String nocTaskId;
    private String rewardOwnerContactNo;
    private Long sendTryCount;
    private LocalDateTime removeDate;
    private String policyEndorsementNumber;

}
