package com.anadolusigorta.campaignmanagement.domain.campaignstatus.model;

import java.util.stream.Stream;

public enum CampaignSearchApprovalEnum {
    ACTION_NEEDED_CAMPAIGNS("İşlem Bekleyen Kampanyalar"),
    PENDING_CAMPAIGNS("Onay Bekleyen Kampanyalar"),
    REJECTED_CAMPAIGNS("Reddedilen Kampanyalar"),
    EMPTY("");

    private final String value;

    CampaignSearchApprovalEnum(String value) { this.value = value; }

    public String getValue() { return value; }

    public static CampaignSearchApprovalEnum of(String value) {
        if(value != null){
            return Stream.of(CampaignSearchApprovalEnum.values())
                    .filter(status -> status.value.equals(value)).findFirst().orElseThrow();
        }
        return CampaignSearchApprovalEnum.EMPTY;
    }
}
