package com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionCheckOperationType;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionDetail;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionOperationType;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionType;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Arrays;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sale_transaction_detail")
public class SaleTransactionalDetailEntity extends AbstractEntity {

    @Column(name = "transaction_id",unique = true)
    private String transactionId;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "campaign_id")
    private Long campaignId;

    @Column(name = "rule_group_id")
    private Long ruleGroupId;

    @Column(name = "transaction_data",length = 5000)
    private String transactionData;

    @Enumerated(EnumType.STRING)
    private SaleTransactionCheckOperationType checkOperationType;

    @Enumerated(EnumType.STRING)
    private SaleTransactionOperationType saleOperation;

    @Enumerated(EnumType.STRING)
    private SaleTransactionType saleTransactionType;

    private String campaignCode;





    public SaleTransactionDetail toModel() {
        return SaleTransactionDetail.builder()
                .id(getId())
                .transactionId(transactionId)
                .contactNo(contactNumber)
                .campaignId(campaignId)
                .ruleGroupId(ruleGroupId)
                .saleTransactionType(saleTransactionType)
                .checkOperationType(checkOperationType)
                .saleOperation(saleOperation)
                .campaignCode(campaignCode)
                .transactionData(transactionData!=null && !transactionData.equals("")?
                        Arrays.stream(transactionData.split(",")).map(entry -> entry.split(":"))
                                .collect(Collectors.toMap(entry -> Long.valueOf(entry[0]),
                                        entry -> Long.valueOf(entry[1]))):null)
                .build();
    }

    public static SaleTransactionalDetailEntity fromModel(SaleTransactionDetail saleTransactionDetail){
        var entity=new SaleTransactionalDetailEntity();
        entity.setId(saleTransactionDetail.getId());
        entity.setContactNumber(saleTransactionDetail.getContactNo());
        entity.setTransactionId(saleTransactionDetail.getTransactionId());
        entity.setCampaignId(saleTransactionDetail.getCampaignId());
        entity.setRuleGroupId(saleTransactionDetail.getRuleGroupId());
        entity.setCheckOperationType(saleTransactionDetail.getCheckOperationType());
        entity.setSaleOperation(saleTransactionDetail.getSaleOperation());
        entity.setSaleTransactionType(saleTransactionDetail.getSaleTransactionType());
        entity.setCampaignCode(saleTransactionDetail.getCampaignCode());
        entity.setTransactionData(saleTransactionDetail.getTransactionData()!=null?
                saleTransactionDetail.getTransactionData()
                        .keySet()
                        .stream().map(key -> key + ":" + saleTransactionDetail.getTransactionData()
                        .get(key)).collect(Collectors.joining(",")):null);
        return entity;
    }
}
