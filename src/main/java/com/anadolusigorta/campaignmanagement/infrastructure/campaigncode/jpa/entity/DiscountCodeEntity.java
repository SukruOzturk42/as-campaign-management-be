package com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.DiscountCode;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeStatusEnum;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.DiscountCodeInformationEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "discount_code",uniqueConstraints = { @UniqueConstraint(columnNames = { "discount_code_information_id", "code" }) })
public class DiscountCodeEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "discount_code_information_id", nullable = false)
    private DiscountCodeInformationEntity discountCodeInformation;

    @Column(name = "code")
    private String code;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "contact_number")
    private String contactNumber;

    @Enumerated(EnumType.STRING)
    private CodeStatusEnum codeStatusEnum;

    public DiscountCode toModel() {
        return DiscountCode.builder()
                .discountCodeInformation(discountCodeInformation.toModel())
                .code(code)
                .isActive(isActive)
                .contactNumber(contactNumber)
                .codeStatusEnum(codeStatusEnum)
                .build();
    }

}
