package com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.SaleCodeTypeEnum;
import com.anadolusigorta.campaignmanagement.domain.sale.model.CreateNotifySaleCampaign;
import com.anadolusigorta.campaignmanagement.domain.sale.model.SaleOperationsRequests;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionOperationType;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.common.util.GlobalGson;
import com.google.gson.Gson;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sale_campaign_request")
public class SaleCampaignRequestEntity extends AbstractEntity {

    private String contactNumber;

    private String campaignCode;

    private String transactionalId;

    private SaleTransactionOperationType requestType;

    private Long campaignId;

    private Long ruleGroupId;

    private Long campaignVersion;

    private String policyNumber;

    private LocalDateTime policyStartDate;

    private LocalDateTime policyCreateDate;

    private String newPolicy;

    private String proposalNumber;

    private String revisionNumber;

    private float grossPremium;

    private float netPremium;

    private String discountType;

    private String discountValue;

    @Enumerated(EnumType.STRING)
    private SaleCodeTypeEnum codeType;

    private String oldProposalNumber;

    private String oldRevisionNumber;

    @Column(length = 5000)
    private String request;

    public static SaleCampaignRequestEntity fromModel(CreateNotifySaleCampaign createNotifySaleCampaign){
        return SaleCampaignRequestEntity.builder()
                .requestType(createNotifySaleCampaign.getRequestType())
                .transactionalId(createNotifySaleCampaign.getTransactionId())
                .contactNumber(createNotifySaleCampaign.getContactNumber())
                .campaignId(createNotifySaleCampaign.getCampaignId())
                .request(GlobalGson.get().toJson(createNotifySaleCampaign.getRequest()))
                .build();
    }

    public SaleOperationsRequests toModel() {
        return SaleOperationsRequests.builder()
                .id(super.getId())
                .transactionId(transactionalId)
                .request(request)
                .contactNumber(contactNumber)
                .createdAt(getCreatedAt())
                .build();
    }

}
