package com.anadolusigorta.campaignmanagement.domain.policyinformation.port;

import com.anadolusigorta.campaignmanagement.domain.policyinformation.model.PolicyInformation;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyInformationPort {
    PolicyInformation getLatestPolicyInformationByPolicyNumber(String policyNumber);
}
