package com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.sale.model.CreateNotifySaleCampaign;
import com.anadolusigorta.campaignmanagement.domain.sale.model.SoldPolicyDetail;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sold_policy_detail")
public class SoldPolicyDetailEntity extends AbstractEntity {

    @Column(name = "policy_number")
    private String policyNumber;

    @Column(name = "policy_create_date")
    private LocalDateTime policyCreateDate;

    @Column(name = "policy_start_date")
    private LocalDateTime policyStartDate;

    @Column(name = "new_policy")
    private String newPolicy;

    @Column(name = "proposal_number")
    private String proposalNumber;

    @Column(name = "revision_number")
    private String revisionNumber;

    @Column(name = "gross_premium")
    private float grossPremium;

    @Column(name = "net_premium")
    private float netPremium;

    @Column(name = "discount_type")
    private String discountType;

    @Column(name = "discount_value")
    private String discountValue;

    @OneToOne
    @JoinColumn(name = "sale_campaign_id")
    private SaleCampaignEntity saleCampaign;

    public SoldPolicyDetail toModel() {
        return SoldPolicyDetail.builder()
                .policyNumber(policyNumber)
                .proposalNumber(proposalNumber)
                .grossPremium(grossPremium)
                .netPremium(netPremium)
                .revisionNumber(revisionNumber)
                .newPolicy(newPolicy)
                .policyCreateDate(policyCreateDate)
                .policyStartDate(policyStartDate)
                .discountValue(discountValue)
                .build();
    }

    public static SoldPolicyDetailEntity fromModel(CreateNotifySaleCampaign.SoldPolicyDetail soldPolicyDetail){
        var soldPolicyDetailEntity = new SoldPolicyDetailEntity();
        soldPolicyDetailEntity.setPolicyCreateDate(soldPolicyDetail.getPolicyCreateDate());
        soldPolicyDetailEntity.setPolicyStartDate(soldPolicyDetail.getPolicyStartDate());
        soldPolicyDetailEntity.setNewPolicy(soldPolicyDetail.getNewPolicy());
        soldPolicyDetailEntity.setGrossPremium(soldPolicyDetail.getGrossPremium());
        soldPolicyDetailEntity.setNetPremium(soldPolicyDetail.getNetPremium());
        soldPolicyDetailEntity.setPolicyNumber(soldPolicyDetail.getPolicyNumber());
        soldPolicyDetailEntity.setProposalNumber(soldPolicyDetail.getProposalNumber());
        soldPolicyDetailEntity.setRevisionNumber(soldPolicyDetail.getRevisionNumber());
        soldPolicyDetailEntity.setDiscountType(soldPolicyDetail.getDiscount().getDiscountType());
        soldPolicyDetailEntity.setDiscountValue(soldPolicyDetail.getDiscount().getDiscountValue());
        return soldPolicyDetailEntity;
    }
}
