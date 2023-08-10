package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CreateGiftCodeInformation;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateGiftCodeInformationRequest {

    private Long id;

    @NotNull
    private Long companyInformationId;

    @NotNull
    private Long rewardGiftTicketTypeId;

    @NotNull
    private List<ThirdPartyGiftCodeRequest> giftCodes=new ArrayList<>();

    public CreateGiftCodeInformation toModel() {
        return CreateGiftCodeInformation.builder()
                .id(id)
                .rewardCompanyInformationId(companyInformationId)
                .rewardGiftTicketTypeId(rewardGiftTicketTypeId)
                .giftCodes(giftCodes.stream()
                        .map(item-> CreateGiftCodeInformation.ThirdPartyGiftCode.builder()
                                .code(item.code)
                                .link(item.link)
                                .build())
                        .toList())
                .build();
    }

    @Getter
    @Setter
    @Builder
    public static class ThirdPartyGiftCodeRequest {
        public static final String EXCEL_CODE_HEADER_NAME = "KOD";
        public static final String EXCEL_SHORT_LINK_HEADER_NAME = "SHORT_LINK";

        @JsonProperty(EXCEL_CODE_HEADER_NAME)
        @SerializedName(EXCEL_CODE_HEADER_NAME)
        String code;

        @JsonProperty(EXCEL_SHORT_LINK_HEADER_NAME)
        @SerializedName(EXCEL_SHORT_LINK_HEADER_NAME)
        String link;


    }

}
