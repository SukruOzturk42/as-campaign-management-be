package com.anadolusigorta.campaignmanagement.domain.campaigncode.model;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftTicketType;
import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardCompanyInformation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GiftCodeInformation implements Serializable {

    private Long id;

    private RewardCompanyInformation rewardCompanyInformation;

    private Long totalCodeCount;

    private Long usedCodeCount;

    private String uploadFileEnum;

    private RewardGiftTicketType rewardGiftTicketType;

    private LocalDateTime codeExpirationDate;

}
