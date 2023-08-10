package com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleGiftCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleGiftCodeInformationDetail;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "policy_sale_gift_code_information_detail")
public class PolicySaleGiftCodeInformationDetailEntity extends AbstractEntity {
    private String updater;

    private String description;

    private Long codeCount;

    @ManyToOne
    @JoinColumn(name= "policy_sale_gift_code_information_id")
    private PolicySaleGiftCodeInformationEntity policySaleGiftCodeInformation;

    public PolicySaleGiftCodeInformationDetail toModel() {
        return PolicySaleGiftCodeInformationDetail.builder()
                .id(getId())
                .updater(updater)
                .description(description)
                .createdAt(getCreatedAt())
                .codeCount(codeCount)
                .build();
    }
}
