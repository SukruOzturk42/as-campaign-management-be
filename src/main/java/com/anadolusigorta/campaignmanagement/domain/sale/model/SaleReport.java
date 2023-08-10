package com.anadolusigorta.campaignmanagement.domain.sale.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleReport {

    private Long id;

    private Long campaignId;

    private Long campaignVersion;

    private String campaignName;

    private String contactNumber;

    private String rewardOwnerContactNumber;

    private Long ruleGroupId;

    private String ruleGroupName;

    private String policyNumber;

    private String proposalNumber;

    private String requestType;

    private LocalDateTime createDate;

    private LocalDateTime sendDate;

    private LocalDateTime lastSendDate;

    private String sendType;

    private Long nocTaskId;

    private String sendStatus;

    private String rewardDescription;

    private String discount;

    private String discountCode;

    private String giftCode;

    private Long rewardGiftId;

    private Long nocStatusCode;

    private Long sendTryCount;

}
