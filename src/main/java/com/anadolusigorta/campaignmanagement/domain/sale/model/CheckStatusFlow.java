package com.anadolusigorta.campaignmanagement.domain.sale.model;


import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;

import java.util.stream.Stream;

public enum CheckStatusFlow {


    LIMIT("limitControl"),
    EXT_CODE_VALID("extCodeValid"),
    CODE_VALID("codeValid"),
    OLD_PROPOSAL("oldProposalControl"),
    POLICY_SEARCH("policySearch"),
    PROPOSAL_ISSUE_DATE("proposalIssueDateCheck"),
    IS_BANK_CONTACT("isBankContact");

    private final String value;

    CheckStatusFlow(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CheckStatusFlow of(String value) {
        return Stream.of(CheckStatusFlow.values())
                .filter(status -> status.value.equals(value))
                .findFirst()
                .orElseThrow(()->new CampaignManagementException("check.flow.type.not.found"));
    }
}
