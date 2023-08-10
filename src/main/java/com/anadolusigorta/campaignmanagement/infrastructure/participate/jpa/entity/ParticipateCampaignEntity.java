package com.anadolusigorta.campaignmanagement.infrastructure.participate.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.participate.model.ParticipatedCustomerCampaign;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignEntity;
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
@Table(name = "participate_campaign")
public class ParticipateCampaignEntity extends AbstractEntity {

    @Column(name = "customer_number")
    private String customerNumber;

    @OneToOne
    @JoinColumn(name = "campaign_information_id")
    private CampaignInformationEntity campaignInformation;

    @OneToOne
    @JoinColumn(name = "campaign_version_id")
    private CampaignVersionEntity campaignVersion;

    @OneToOne
    @JoinColumn(name = "rule_group_id")
    private CampaignRuleGroupEntity ruleGroup;

    @OneToOne
    @JoinColumn(name = "participate_reward_id")
    private ParticipateCampaignRewardEntity participateCampaignReward;

    @Column(name = "order_of_participation")
    private Integer orderOfParticipation=1;

    @Column(name = "number_of_participation")
    private Integer numberOfParticipation=1;

    private String transactionId;

    public ParticipatedCustomerCampaign toModel(){

       return  ParticipatedCustomerCampaign.builder()
                .campaignInformation(campaignInformation.toModel())
                .customerNumber(customerNumber)
                .numberOfParticipation(numberOfParticipation)
                .orderOfParticipation(orderOfParticipation)
               .campaignReward(participateCampaignReward.toModel())
                .build();
    }




}
