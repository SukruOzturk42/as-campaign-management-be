package com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.RewardContactRoleTypeEnum;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.SaleCodeTypeEnum;
import com.anadolusigorta.campaignmanagement.domain.sale.model.CreateNotifySaleCampaign;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionOperationType;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class NotifySaleCampaignRequest {

    @NotNull
    private String contactNumber;

    private String campaignCode;

    @NotNull
    private String transactionId;

    private String previousTransactionId;

    @NotNull
    private Long campaignId;

    @NotNull
    private Long ruleGroupId;

    @NotNull
    private Long campaignVersion;

    private SaleTransactionOperationType requestType;

    private TransactionDetail transactionDetail;

    private String oldProposalNumber;

    private String oldRevisionNumber;

    private SaleCodeTypeEnum codeType;

    private List<NotifySaleCampaignRequest> insured;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TransactionDetail {

        private String policyNumber;

        private String policyType;

        @NotNull
        private String policyStartDate;

        @NotNull
        private String policyCreateDate;

        @NotNull
        private String newPolicy;

        private String proposalNumber;

        private String revisionNumber;

        private float grossPremium;

        private float netPremium;

        private Discount appliedDiscount;

        private String  policyOwnerContactNumber;

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Discount {

        private String discountType;

        private String discountValue;

    }



    public CreateNotifySaleCampaign toModel() {
        log.info(String.format("Notify request :%s",this));
        return CreateNotifySaleCampaign.builder()
                .transactionId(transactionId)
                .contactNumber(contactNumber)
                .campaignCode(campaignCode)
                .campaignId(campaignId)
                .ruleGroupId(ruleGroupId)
                .campaignVersion(campaignVersion)
                .requestType(requestType)
                .soldPolicyDetail(transactionDetail!=null?
                        mapToTransactionDetail(transactionDetail,contactNumber)
                        :null)
                .oldProposalNumber(oldProposalNumber)
                .oldRevisionNumber(oldRevisionNumber)
                .codeType(codeType)
                .insured(insured!=null?toListModel(insured):null)
                .request(this)
                .build();
    }

    private List<CreateNotifySaleCampaign> toListModel(List<NotifySaleCampaignRequest> requests){
        return requests.stream()
                .map(NotifySaleCampaignRequest::toModel)
                .collect(Collectors.toList());
    }

    private CreateNotifySaleCampaign.SoldPolicyDetail mapToTransactionDetail(TransactionDetail transactionDetail
            ,String contactNumber) {
        return CreateNotifySaleCampaign.SoldPolicyDetail.builder()
                .policyNumber(transactionDetail.policyNumber)
                .policyType(transactionDetail.getPolicyType())
                .policyStartDate(LocalDateTime.parse(transactionDetail.policyStartDate))
                .policyCreateDate(LocalDateTime.parse(transactionDetail.policyCreateDate))
                .newPolicy(transactionDetail.newPolicy)
                .proposalNumber(transactionDetail.proposalNumber)
                .revisionNumber(transactionDetail.revisionNumber)
                .grossPremium(transactionDetail.grossPremium)
                .netPremium(transactionDetail.netPremium)
                .policyOwnerContactNumber(transactionDetail.getPolicyOwnerContactNumber())
                .discount(mapToDiscountModel(transactionDetail.appliedDiscount))
                .policyOwnerContactNumber(transactionDetail.policyOwnerContactNumber)
                .rewardContactRoleType(transactionDetail.policyOwnerContactNumber!=null?
                        transactionDetail.policyOwnerContactNumber.equals(contactNumber)
                ? RewardContactRoleTypeEnum.INSURER:RewardContactRoleTypeEnum.INSURED
                        :RewardContactRoleTypeEnum.INSURER)
                .build();
    }

    private CreateNotifySaleCampaign.Discount mapToDiscountModel(Discount discount) {
        return CreateNotifySaleCampaign.Discount.builder()
                .discountType(discount.discountType)
                .discountValue(discount.discountValue)
                .build();
    }

}
