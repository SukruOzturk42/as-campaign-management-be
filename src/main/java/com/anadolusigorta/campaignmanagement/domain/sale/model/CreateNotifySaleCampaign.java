package com.anadolusigorta.campaignmanagement.domain.sale.model;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.RewardContactRoleTypeEnum;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.SaleCodeTypeEnum;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionOperationType;
import com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.request.NotifySaleCampaignRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateNotifySaleCampaign {

    private String transactionId;

    private String contactNumber;

    private String campaignCode;

    private Long campaignId;

    private Long ruleGroupId;

    private Long campaignVersion;

    private SoldPolicyDetail soldPolicyDetail=SoldPolicyDetail.builder().build();

    private SaleTransactionOperationType requestType;

    private String oldProposalNumber;

    private String oldRevisionNumber;

    private SaleCodeTypeEnum codeType;


    private List<CreateNotifySaleCampaign> insured;

    private NotifySaleCampaignRequest request;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SoldPolicyDetail {

        private String policyNumber;

        private String policyType;

        private LocalDateTime policyStartDate;

        private LocalDateTime policyCreateDate;

        private String newPolicy;

        private String proposalNumber;

        private String revisionNumber;

        private float grossPremium;

        private float netPremium;

        private String  policyOwnerContactNumber;

        private RewardContactRoleTypeEnum rewardContactRoleType;

        private Discount discount= Discount.builder().build();

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Discount {

        private String discountType;

        private String discountValue;

    }

    public Boolean hasProposal(){
        return this.oldProposalNumber != null && this.oldRevisionNumber!=null;
    }
}
