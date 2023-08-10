package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.GiftCodeInformation;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity.RewardGiftTicketTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.common.enums.UploadFileEnum;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.RewardCompanyInformationEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "gift_code_information")
public class GiftCodeInformationEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "reward_company_information_id")
    private RewardCompanyInformationEntity rewardCompanyInformation;

    @Enumerated(EnumType.STRING)
    private UploadFileEnum uploadFileEnum;

    @ManyToOne
    @JoinColumn(name = "gift_code_reward_type_id")
    private RewardGiftTicketTypeEntity giftCodeRewardType;

    public GiftCodeInformation toModel() {
        return GiftCodeInformation.builder()
                .id(super.getId())
                .rewardCompanyInformation(rewardCompanyInformation.toModel())
                .uploadFileEnum(uploadFileEnum != null ? uploadFileEnum.getValue() : "")
                .rewardGiftTicketType(giftCodeRewardType.toModel())
                .codeExpirationDate(null)
                .build();
    }

}
