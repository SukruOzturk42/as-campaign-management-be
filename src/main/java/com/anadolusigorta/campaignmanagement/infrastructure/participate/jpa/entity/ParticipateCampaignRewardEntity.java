package com.anadolusigorta.campaignmanagement.infrastructure.participate.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.participate.model.ParticipatedCustomerCampaign;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignInformationEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignRuleGroupEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignVersionEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "participate_campaign_reward")
public class ParticipateCampaignRewardEntity extends AbstractEntity {


    @OneToOne
    @JoinColumn(name = "participate_id")
    private ParticipateCampaignEntity participateCampaign;


    @OneToOne
    @JoinColumn(name = "participate_gift_reward_id")
    private ParticipateCampaignGiftRewardEntity giftReward;

    @OneToOne
    @JoinColumn(name = "participate_discount_reward_id")
    private ParticipateCampaignDiscountRewardEntity discountReward;


    public ParticipatedCustomerCampaign.CampaignReward toModel(){

       return  ParticipatedCustomerCampaign.CampaignReward.builder()
               .campaignRewardGift(giftReward!=null?giftReward.toModel():null)
               .campaignRewardDiscount(discountReward!=null?discountReward.toModel():null)
                .build();
    }




}
