package com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.CreatePolicySaleGiftCodeInformation;
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
public class CreatePolicySaleGiftCodeInformationRequest {

    private Long id;

    private Long rewardCompanyInformationId;

    private Long rewardGiftTicketTypeId;

    @NotNull
    private List<GiftCodeRequest> giftCodes=new ArrayList<>();

    public CreatePolicySaleGiftCodeInformation toModel() {
        return CreatePolicySaleGiftCodeInformation.builder()
                .id(id)
                .rewardCompanyInformationId(rewardCompanyInformationId)
                .rewardGiftTicketTypeId(rewardGiftTicketTypeId)
                .giftCodes(giftCodes.stream()
                        .map(item-> CreatePolicySaleGiftCodeInformation.GiftCode.builder()
                                .code(item.code)
                                .link(item.link)
                                .build())
                        .toList())
                .build();
    }

    @Getter
    @Setter
    @Builder
    public static class GiftCodeRequest {
        public static final String EXCEL_CODE_HEADER_NAME = "Çek Kod";
        public static final String EXCEL_SHORT_LINK_HEADER_NAME = "Short Link";

        @JsonProperty(EXCEL_CODE_HEADER_NAME)
        @SerializedName(EXCEL_CODE_HEADER_NAME)
        String code;

        @JsonProperty(EXCEL_SHORT_LINK_HEADER_NAME)
        @SerializedName(EXCEL_SHORT_LINK_HEADER_NAME)
        String link;


    }
}
