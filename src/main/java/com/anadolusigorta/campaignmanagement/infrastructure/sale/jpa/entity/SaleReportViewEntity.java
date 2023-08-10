package com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.sale.model.SaleReport;
import lombok.*;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SALE_REPORT_VIEW")
@Immutable
public class SaleReportViewEntity implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "campaign_id")
    private Long campaignId;

    @Column(name = "campaign_version")
    private Long campaignVersion;

    @Column(name = "campaign_name")
    private String campaignName;
    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "reward_owner_contact_no")
    private String rewardOwnerContactNumber;

    @Column(name = "rule_group_id")
    private Long ruleGroupId;

    @Column(name = "rule_group_name")
    private String ruleGroupName;

    @Column(name = "policy_number")
    private String policyNumber;

    @Column(name = "proposal_number")
    private String proposal;

    @Column(name = "request_type")
    private String requestType;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "send_date")
    private LocalDateTime sendDate;

    @Column(name = "last_send_date")
    private LocalDateTime lastSendDate;

    @Column(name = "send_type")
    private String sendType;

    @Column(name = "noc_task_id")
    private Long nocTaskId;

    @Column(name = "send_status")
    private String sendStatus;

    @Column(name = "reward_description")
    private String rewardDescription;

    @Column(name = "discount")
    private String discount;

    @Column(name = "discount_code")
    private String discountCode;

    @Column(name = "reward_gift_id")
    private Long rewardGiftId;

    @Column(name = "gift_code")
    private String giftCode;

    @Column(name = "send_status_code")
    private Long nocStatusCode;

    @Column(name = "send_try_count")
    private Long sendTryCount;



    public SaleReport toModel(){
        return SaleReport.builder()
                .campaignId(campaignId)
                .campaignName(campaignName)
                .contactNumber(contactNumber)
                .campaignVersion(campaignVersion)
                .rewardOwnerContactNumber(rewardOwnerContactNumber)
                .ruleGroupId(ruleGroupId)
                .ruleGroupName(ruleGroupName)
                .policyNumber(policyNumber)
                .proposalNumber(proposal)
                .requestType(requestType)
                .createDate(createDate)
                .sendDate(sendDate)
                .lastSendDate(lastSendDate)
                .sendType(sendType)
                .nocTaskId(nocTaskId)
                .sendStatus(sendStatus)
                .rewardDescription(rewardDescription)
                .discount(discount)
                .discountCode(discountCode)
                .giftCode(giftCode)
                .rewardGiftId(rewardGiftId)
                .nocStatusCode(nocStatusCode)
                .sendTryCount(sendTryCount)
                .build();
    }



}
