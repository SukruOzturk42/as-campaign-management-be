package com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CheckSaleStatus;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.SaleCodeTypeEnum;
import com.anadolusigorta.campaignmanagement.domain.sale.model.SaleOperationsRequests;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionCheckOperationType;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.common.util.GlobalGson;
import com.google.gson.Gson;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "check_campaign_sale_request")
public class CheckCodeRequestEntity extends AbstractEntity {

    private String contactNumber;

    private String transactionId;

    @Enumerated(EnumType.STRING)
    private SaleTransactionCheckOperationType requestType;

    private String campaignCode;

    private Long campaignId;

    private Long ruleGroupId;

    private Long campaignVersion;

    private LocalDateTime proposalIssueDate;

    @Enumerated(EnumType.STRING)
    private SaleCodeTypeEnum codeType;

    private String oldProposalNumber;

    private String oldRevisionNumber;

    @Column(length = 5000)
    private String request;

    public static CheckCodeRequestEntity fromModel(CheckSaleStatus checkSaleStatus){
        return CheckCodeRequestEntity.builder()
                .transactionId(checkSaleStatus.getTransactionId())
                .campaignId(checkSaleStatus.getCampaignId())
                .ruleGroupId(checkSaleStatus.getRuleGroupId())
                .campaignVersion(checkSaleStatus.getCampaignVersion())
                .contactNumber(checkSaleStatus.getInsuredCheckStatus() != null ? checkSaleStatus.getInsuredCheckStatus().stream()
                        .map(CheckSaleStatus::getContactNumber)
                        .collect(Collectors.joining(",")) : checkSaleStatus.getContactNumber())
                .transactionId(checkSaleStatus.getInsuredCheckStatus() != null ? checkSaleStatus.getInsuredCheckStatus().stream()
                        .map(CheckSaleStatus::getTransactionId)
                        .collect(Collectors.joining(",")) : checkSaleStatus.getTransactionId())
                .requestType(checkSaleStatus.getOperationType())
                .campaignCode(checkSaleStatus.getCampaignCode())
                .proposalIssueDate(checkSaleStatus.getProposalIssueDate())
                .codeType(checkSaleStatus.getCodeType())
                .oldProposalNumber(checkSaleStatus.getOldProposalNumber())
                .oldRevisionNumber(checkSaleStatus.getOldRevisionNumber())
                .request(GlobalGson.get().toJson(checkSaleStatus.getRequest()))
                .build();
    }

    public SaleOperationsRequests toModel() {
        return SaleOperationsRequests.builder()
                .id(super.getId())
                .transactionId(transactionId)
                .request(request)
                .contactNumber(contactNumber)
                .createdAt(getCreatedAt())
                .build();
    }

}
