package com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.entity;


import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeStatusEnum;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.GiftCode;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleGiftCode;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.GiftCodeInformationEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "policy_sale_gift_code")
public class PolicySaleGiftCodeEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "policy_sale_gift_code_information_id", nullable = false)
    private PolicySaleGiftCodeInformationEntity policySaleGiftCodeInformation;

    @Column(name = "code")
    private String code;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "contact_number")
    private String contactNumber;

    @Enumerated(EnumType.STRING)
    private CodeStatusEnum codeStatus;

    @Column(name = "qr_code")
    private String qrCodeUrl;

    public PolicySaleGiftCode toModel(){
        return  PolicySaleGiftCode.builder()
                .id(super.getId())
                .code(code)
                .codeStatusEnum(codeStatus)
                .giftCodeInformation(policySaleGiftCodeInformation.toModel())
                .contactNumber(contactNumber)
                .isActive(isActive)
                .qrCodeUrl(qrCodeUrl)
                .build();

    }
}
