package com.anadolusigorta.campaignmanagement.domain.policyinformation.facade;

import com.anadolusigorta.campaignmanagement.domain.policyinformation.model.PolicyInformation;
import com.anadolusigorta.campaignmanagement.domain.policyinformation.port.PolicyInformationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PolicyInformationFacade {

    private final PolicyInformationPort policyInformationPort;

    public PolicyInformation getLatestPolicyInformationByPolicyNumber(String policyNumber) {
        return policyInformationPort.getLatestPolicyInformationByPolicyNumber(policyNumber);
    }
}
