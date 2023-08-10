package com.anadolusigorta.campaignmanagement.domain.campaign.model;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateGiftCodeInformation {

    private Long id;

    private Long rewardCompanyInformationId;

    private Long totalCodeCount;

    private Long usedCodeCount;

    private Long rewardGiftTicketTypeId;

    private List<ThirdPartyGiftCode> giftCodes;

    @Getter
    @Setter
    @Builder
    public static class ThirdPartyGiftCode {

        String code;

        String link;

    }


}
