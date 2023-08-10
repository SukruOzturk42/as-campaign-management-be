package com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePolicySaleGiftCodeInformation {

    private Long id;

    private Long rewardCompanyInformationId;

    private Long rewardGiftTicketTypeId;

    private List<GiftCode> giftCodes;

    @Getter
    @Setter
    @Builder
    public static class GiftCode {

        String code;

        String link;

    }

}
