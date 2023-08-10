package com.anadolusigorta.campaignmanagement.domain.saletransaction.model;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleTransactionDetail {

    private Long id;
    private String transactionId;
    private String contactNo;
    private Long campaignId;
    private Long ruleGroupId;
    private Map<Long, Long> transactionData;
    private SaleTransactionCheckOperationType checkOperationType;
    private SaleTransactionOperationType saleOperation;
    private SaleTransactionType saleTransactionType;
    private String campaignCode;

    public boolean isTransactionOperationEnable(SaleTransactionDetail saleTransactionDetailRequest){
        if(saleTransactionDetailRequest.saleOperation.equals(SaleTransactionOperationType.POLICY)){
            return this.getCheckOperationType().equals(SaleTransactionCheckOperationType.SAVE_POLICY);
        }
       return true;
    }

    public boolean isCorrectSaleTransaction(SaleTransactionDetail saleTransactionDetailRequest){

        var campaignTransactionRuleGroup=this.transactionData.get(saleTransactionDetailRequest.getCampaignId());
       return this.contactNo.equals(saleTransactionDetailRequest.contactNo) &&
               campaignTransactionRuleGroup.equals(saleTransactionDetailRequest.ruleGroupId);

    }

    public boolean isCorrectCode(SaleTransactionDetail saleTransactionDetailRequest){
        if(this.campaignCode!=null){
            return saleTransactionDetailRequest.getCampaignCode().equals(this.campaignCode);
        }else {
            return saleTransactionDetailRequest.campaignCode==null;
        }

    }

}
