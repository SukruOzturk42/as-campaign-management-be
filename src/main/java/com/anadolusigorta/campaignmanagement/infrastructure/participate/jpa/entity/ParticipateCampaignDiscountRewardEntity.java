package com.anadolusigorta.campaignmanagement.infrastructure.participate.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.participate.model.ParticipatedCustomerCampaign;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.RuleGroupRewardDiscountEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity.DiscountCodeEntity;
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
@Table(name = "participate_discount_reward")
public class ParticipateCampaignDiscountRewardEntity extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "participate_reward_id")
    private ParticipateCampaignRewardEntity participateCampaignReward;

    @OneToOne
    @JoinColumn(name="campaign_code_id")
    private DiscountCodeEntity campaignCode;

    @OneToOne
    @JoinColumn(name="rule_group_reward_discount_id")
    RuleGroupRewardDiscountEntity discount;

    @Column(name = "is_sent")
    private Boolean isSent;

    @Column(name = "send_date")
    private LocalDateTime rewardSendDate;




    public ParticipatedCustomerCampaign.CampaignReward.CampaignRewardDiscount toModel(){

       return   ParticipatedCustomerCampaign.CampaignReward.CampaignRewardDiscount.builder()
               .value(discount.getValue())
               .name(discount.getDiscountCodeInformation()!=null?
                       discount.getDiscountCodeInformation().getCodeGroupName():null)
               .rewardDiscountType(discount.getDiscountKind().toModel())
               .discountCode(campaignCode!=null?campaignCode.toModel():null)
               .build();
    }




}
