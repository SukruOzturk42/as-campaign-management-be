package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.DiscountCodeInformationDetail;
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
@Table(name = "discount_code_information_detail")
public class DiscountCodeInformationDetailEntity extends AbstractEntity {

    private String updater;

    private String description;

    private Long codeCount;

    @ManyToOne
    @JoinColumn(name = "discount_code_information_id")
    private DiscountCodeInformationEntity discountCodeInformation;

    public DiscountCodeInformationDetail toModel() {
        return DiscountCodeInformationDetail.builder()
                .id(getId())
                .updater(updater)
                .description(description)
                .createdAt(getCreatedAt())
                .build();
    }

}
