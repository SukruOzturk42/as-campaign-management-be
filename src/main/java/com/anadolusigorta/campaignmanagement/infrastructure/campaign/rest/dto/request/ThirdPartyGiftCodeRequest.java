package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ThirdPartyGiftCodeRequest {
    public static final String EXCEL_CODE_HEADER_NAME = "code";
    public static final String EXCEL_SHORT_LINK_HEADER_NAME = "link";

    @JsonProperty(EXCEL_CODE_HEADER_NAME)
    @SerializedName(EXCEL_CODE_HEADER_NAME)
    String code;

    @JsonProperty(EXCEL_SHORT_LINK_HEADER_NAME)
    @SerializedName(EXCEL_SHORT_LINK_HEADER_NAME)
    String link;


}
