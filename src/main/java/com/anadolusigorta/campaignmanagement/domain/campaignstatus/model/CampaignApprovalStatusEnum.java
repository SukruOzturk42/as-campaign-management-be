package com.anadolusigorta.campaignmanagement.domain.campaignstatus.model;

import java.util.stream.Stream;

public enum CampaignApprovalStatusEnum {
    TEMPLATE_CAMPAIGN("TEMPLATE_CAMPAIGN"),
    SENT_FOR_APPROVAL_CAMPAIGN("SENT_FOR_APPROVAL_CAMPAIGN"),
    APPROVED_CAMPAIGN("APPROVED_CAMPAIGN"),
    REJECTED_CAMPAIGN("REJECTED_CAMPAIGN");

    private final String value;

    CampaignApprovalStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CampaignApprovalStatusEnum of(String value) {
        return Stream.of(CampaignApprovalStatusEnum.values())
                .filter(status -> status.value.equals(value)).findFirst().orElseThrow();
    }
}
