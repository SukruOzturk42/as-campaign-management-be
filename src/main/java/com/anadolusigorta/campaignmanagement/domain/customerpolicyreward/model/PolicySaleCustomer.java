package com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicySaleCustomer {

    private Long id;
    private  String customerNo;
    private  PolicySaleGiftCode policySaleGiftCode;
    private  LocalDateTime giftCodeSendDate;
    private PolicySaleGiftCodeSendStatusEnum policySaleGiftCodeSendStatus;
    private PolicySaleRewardCampaign policySaleRewardCampaign;
    private String notificationDeliveryDescription;

}
