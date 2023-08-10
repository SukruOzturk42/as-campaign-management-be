package com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.CustomerExcelSaveStatus;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleRewardCampaign;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.RewardGiftSendMethodTypeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "policy_sale_reward_campaign")
public class PolicySaleRewardCampaignEntity extends AbstractEntity {

    @Column(name = "campaign_name")
    private String campaignName;

    @ManyToOne
    private PolicySaleGiftCodeInformationEntity policySaleGiftCodeInformationEntity;

    @ManyToOne
    private RewardGiftSendMethodTypeEntity rewardGiftSendMethodTypeEntity;

    @OneToMany(mappedBy = "policySaleRewardCampaign",fetch = FetchType.EAGER)
    private Set<PolicySaleCustomerEntity> policySaleCustomers;

    private String customerListFileName;

    @Enumerated(EnumType.STRING)
    private CustomerExcelSaveStatus customerExcelSaveStatus;

    public PolicySaleRewardCampaign toModel() {
        return PolicySaleRewardCampaign.builder()
                .id(getId())
                .campaignName(campaignName)
                .customerFileName(customerListFileName)
                .createDate(getCreatedAt())
                .policySaleGiftCodeInformation(policySaleGiftCodeInformationEntity != null ?
                        policySaleGiftCodeInformationEntity.toModel() : null )
                .rewardGiftSendMethodType(rewardGiftSendMethodTypeEntity.toModel())
                .totalCustomer(customerExcelSaveStatus == CustomerExcelSaveStatus.RECORDED ?
                        policySaleCustomers.size() : 0)
                .unassignedCustomerCount(
                        customerExcelSaveStatus == CustomerExcelSaveStatus.RECORDED ?
                                (int) policySaleCustomers.stream().filter(item -> item.getPolicySaleGiftCode() == null).count()
                        : 0)
                .build();
    }
}
