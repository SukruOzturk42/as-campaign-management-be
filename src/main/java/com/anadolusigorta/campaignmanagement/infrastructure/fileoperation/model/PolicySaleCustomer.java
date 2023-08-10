package com.anadolusigorta.campaignmanagement.infrastructure.fileoperation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Setter;

@Setter
public class PolicySaleCustomer {
    public static final String EXCEL_SHEET_NAME="contacts";
    public static final String RULE_GROUP_CUSTOMER_EXCEL_CUSTOMER_NO_HEADER_NAME="MUSTERI_NO";

    @JsonProperty(value = RULE_GROUP_CUSTOMER_EXCEL_CUSTOMER_NO_HEADER_NAME)
    @SerializedName(RULE_GROUP_CUSTOMER_EXCEL_CUSTOMER_NO_HEADER_NAME)
    String customerNo;

    public String getCustomerNo(){
        return customerNo;
    }


}
