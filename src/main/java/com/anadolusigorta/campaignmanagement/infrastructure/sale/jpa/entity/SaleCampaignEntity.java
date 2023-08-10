package com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.sale.model.SaleInformation;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionOperationType;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignInformationEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignRuleGroupEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sale_campaign")
public class SaleCampaignEntity extends AbstractEntity {

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "campaign_code")
    private String campaignCode;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private CampaignEntity campaign;

    @OneToOne
    @JoinColumn(name = "campaign_information_id")
    private CampaignInformationEntity campaignInformation;

    @OneToOne
    @JoinColumn(name = "rule_group_id")
    private CampaignRuleGroupEntity ruleGroup;

    @Column(name = "campaign_version")
    private Long campaignVersion;

    @Column(name = "order_of_participation")
    private Integer orderOfParticipation = 1;

    @Column(name = "number_of_participation")
    private Integer numberOfParticipation = 1;

    @Column(name = "request_type")
    @Enumerated(EnumType.STRING)
    private SaleTransactionOperationType requestType;

    @OneToOne
    @JoinColumn(name = "sale_reward_gift_id")
    private SaleRewardGiftEntity saleRewardGift;

    @OneToOne
    @JoinColumn(name = "sold_policy_detail_id")
    private SoldPolicyDetailEntity soldPolicyDetail;

    public SaleInformation toModel() {
        return SaleInformation.builder()
                .id(super.getId())
                .campaignInformation(campaignInformation.toModel())
                .campaignRuleGroup(ruleGroup.toModel())
                .soldPolicyDetail(soldPolicyDetail != null ?
                        soldPolicyDetail.toModel() : null)
                .contactNumber(contactNumber)
                .createDate(getCreatedAt())
                .campaignCode(campaignCode)
                .requestType(requestType)
                .saleRewardGift(saleRewardGift != null ?
                        saleRewardGift.toRewardDetailModel() : null)
                .build();
    }
}
