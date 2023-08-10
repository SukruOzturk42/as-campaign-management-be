package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@Builder
public class DiscountCode {

    public static final String EXCEL_SHEET_NAME = "codes";
    public static final String EXCEL_CODE_HEADER_NAME = "KOD";
    public static final String EXCEL_CUSTOMER_NO_HEADER_NAME = "MUSTERI_NO";
    public static final String EXCEL_SHORT_LINK_HEADER_NAME = "LINK";

    @JsonProperty(EXCEL_CODE_HEADER_NAME)
    @SerializedName(EXCEL_CODE_HEADER_NAME)
    String code;

    @JsonProperty(EXCEL_CUSTOMER_NO_HEADER_NAME)
    @SerializedName(EXCEL_CUSTOMER_NO_HEADER_NAME)
    String customerNo;

}
