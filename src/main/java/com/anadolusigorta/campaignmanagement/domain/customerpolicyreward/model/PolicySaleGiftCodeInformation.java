package com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardCompanyInformation;
import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftTicketType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicySaleGiftCodeInformation implements Serializable {

    private Long id;

    private RewardCompanyInformation rewardCompanyInformation;

    private Long totalCodeCount;

    private Long usedCodeCount;

    private String uploadFileEnum;

    private RewardGiftTicketType rewardGiftTicketType;

    private LocalDateTime codeExpirationDate;

    private List<PolicySaleGiftCodeInformationDetail> policySaleGiftCodeInformationDetails;

}
