package com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeStatusEnum;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.GiftCode;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.DiscountCodeInformationEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.GiftCodeInformationEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity.SaleRewardGiftEntity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "gift_code",uniqueConstraints = { @UniqueConstraint(columnNames = { "gift_code_information_id", "code" }) })
@Slf4j
public class GiftCodeEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "gift_code_information_id", nullable = false)
    private GiftCodeInformationEntity giftCodeInformation;

    @Column(name = "code")
    private String code;

    @Column(name = "contact_number")
    private String contactNumber;

    @Enumerated(EnumType.STRING)
    private CodeStatusEnum codeStatusEnum;

    @ManyToOne
    @JoinColumn(name = "sale_gift_id")
    private SaleRewardGiftEntity saleRewardGift;

    private String qrCodeUrl;

    public GiftCode toModel(){
        log.info(String.format(" code model information code id:%s,code%s",super.getId(),code));
       return  GiftCode.builder()
                .id(super.getId())
                .code(code)
                .codeStatusEnum(codeStatusEnum)
                .giftCodeInformation(giftCodeInformation.toModel())
                .contactNumber(contactNumber)
                .qrCodeUrl(qrCodeUrl)
                .build();

    }

}
