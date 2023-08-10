package com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CheckSaleStatus;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.SaleCodeTypeEnum;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionCheckOperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class CheckCampaignStatusRequest {

    @NotNull
    private String contactNumber;

   
    private String policyOwnerContactNumber;


    private String transactionId;

    private String campaignCode;

    private String referenceCampaignCode;

    private String codeType;

    private SaleTransactionCheckOperationType operationType;

    private String oldProposalNumber;

    private String oldRevisionNumber;

    private String salesChannel;

    private LocalDateTime proposalIssueDate;

    private Boolean isActiveContact=Boolean.TRUE;

    @NotNull
    private Long campaignId;

    @NotNull
    private Long ruleGroupId;

    private List<CheckCampaignStatusRequest> insured;

    private String vinNumber;

    public CheckSaleStatus toModel() {
        log.info(String.format("Check request :%s",this));
        return CheckSaleStatus.builder()
                .contactNumber(contactNumber)
                .transactionId(transactionId)
                .campaignCode(campaignCode)
                .codeType(codeType != null ? SaleCodeTypeEnum.valueOf(codeType) : null)
                .campaignId(campaignId)
                .ruleGroupId(ruleGroupId)
                .operationType(operationType)
                .oldProposalNumber(oldProposalNumber)
                .oldRevisionNumber(oldRevisionNumber)
                .proposalIssueDate(proposalIssueDate)
                .referenceCampaignCode(referenceCampaignCode)
                .salesChannel(salesChannel)
                .isActiveContact(isActiveContact)
                .vinNumber(vinNumber)
                .insuredCheckStatus(insured!=null?this.toModelList(insured):null)
                .build();
    }

    private List<CheckSaleStatus> toModelList(List<CheckCampaignStatusRequest> checkSaleStatusList){
        return checkSaleStatusList.stream()
                .map(CheckCampaignStatusRequest::toModel)
                .collect(Collectors.toList());
    }

}
