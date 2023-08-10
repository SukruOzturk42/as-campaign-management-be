package com.anadolusigorta.campaignmanagement.infrastructure.participate.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.participate.model.ParticipatedCustomerCampaign;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.RuleGroupRewardGiftEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity.GiftCodeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "participate_gift_reward")
public class ParticipateCampaignGiftRewardEntity extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "participate_reward_id")
    private ParticipateCampaignRewardEntity participateCampaignReward;

    @OneToOne
    @JoinColumn(name="gift_code_id")
    private GiftCodeEntity giftCode;

    @OneToOne
    @JoinColumn(name="rule_group_reward_gift_id")
    private RuleGroupRewardGiftEntity gift;

    @Column(name = "is_sent")
    private Boolean isSent;

    @Column(name = "send_date")
    private LocalDateTime rewardSendDate;



    public ParticipatedCustomerCampaign.CampaignReward.CampaignRewardGift toModel(){

       return  ParticipatedCustomerCampaign.CampaignReward.CampaignRewardGift.builder()
               .name(gift.getRewardGiftProduct()!=null?gift.getRewardGiftProduct().getName():
                       gift.getGiftCodeInformation().getGiftCodeRewardType().getName())
               .rewardType(gift.getGiftType().getName())
               .giftCode(giftCode !=null? giftCode.toModel():null)
                .build();
    }




}
