package com.anadolusigorta.campaignmanagement.infrastructure.policyinformation.port.adapter;

import com.anadolusigorta.campaignmanagement.domain.policyinformation.model.PolicyInformation;
import com.anadolusigorta.campaignmanagement.domain.policyinformation.port.PolicyInformationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PolicyInformationPortAdapter implements PolicyInformationPort {


    @Override
    public PolicyInformation getLatestPolicyInformationByPolicyNumber(String policyNumber) {
        return null;
    }
}
