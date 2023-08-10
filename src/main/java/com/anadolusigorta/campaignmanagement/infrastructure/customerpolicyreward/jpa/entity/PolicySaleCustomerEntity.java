package com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleCustomer;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleGiftCodeSendStatusEnum;
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
@Table(name = "policy_sale_customer")
public class PolicySaleCustomerEntity extends AbstractEntity {

    private String customerNo;

    @ManyToOne
    @JoinColumn(name = "policy_sale_reward_campaign_id", nullable = false)
    private PolicySaleRewardCampaignEntity policySaleRewardCampaign;

    @OneToOne
    private PolicySaleGiftCodeEntity policySaleGiftCode;

    private LocalDateTime giftCodeSendDate;

    @Enumerated(EnumType.STRING)
    private PolicySaleGiftCodeSendStatusEnum policySaleGiftCodeSendStatus;

    private Boolean isSent = false;

    @Column(name = "description")
    private String description;

    public PolicySaleCustomer toModel(){
        return  PolicySaleCustomer.builder()
                .id(getId())
                .policySaleGiftCode(policySaleGiftCode != null ? policySaleGiftCode.toModel():null)
                .policySaleGiftCodeSendStatus(policySaleGiftCodeSendStatus)
                .customerNo(customerNo)
                .giftCodeSendDate(giftCodeSendDate)
                .notificationDeliveryDescription(description)
                .policySaleRewardCampaign(policySaleRewardCampaign.toModel())
                .build();
    }
}
