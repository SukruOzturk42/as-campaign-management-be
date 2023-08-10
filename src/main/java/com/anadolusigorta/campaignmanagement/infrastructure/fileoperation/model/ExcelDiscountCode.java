package com.anadolusigorta.campaignmanagement.infrastructure.fileoperation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class ExcelDiscountCode implements Serializable {

    public static final String EXCEL_SHEET_NAME = "codes";
    public static final String EXCEL_CUSTOMER_NO_HEADER_NAME = "MUSTERI_NO";

    @JsonProperty(EXCEL_CUSTOMER_NO_HEADER_NAME)
    @SerializedName(EXCEL_CUSTOMER_NO_HEADER_NAME)
    private String customerNo;

}
