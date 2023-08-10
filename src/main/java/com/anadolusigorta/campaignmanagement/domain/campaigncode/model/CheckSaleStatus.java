package com.anadolusigorta.campaignmanagement.domain.campaigncode.model;

import com.anadolusigorta.campaignmanagement.domain.sale.model.SaleRewardGift;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionCheckOperationType;
import com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.request.CheckCodeStatusRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckSaleStatus implements Serializable {

    private Long id;

    private String contactNumber;

    private String transactionId;

    private String campaignCode;

    private String referenceCampaignCode;

    private SaleCodeTypeEnum codeType;

    private SaleTransactionCheckOperationType operationType;

    private Long campaignVersion;

    private String oldProposalNumber;

    private String oldRevisionNumber;

    private Long campaignId;

    private Long ruleGroupId;

    private Boolean success;

    private LocalDateTime proposalIssueDate;

    private String salesChannel;

    public Boolean hasProposal(){
        return this.oldProposalNumber != null && this.oldRevisionNumber!=null;
    }

    private List<CheckSaleStatus> insuredCheckStatus;

    private Boolean isActiveContact=Boolean.TRUE;

    private String discountValue;


    private String vinNumber;

    private CheckCodeStatusRequest request;






}
