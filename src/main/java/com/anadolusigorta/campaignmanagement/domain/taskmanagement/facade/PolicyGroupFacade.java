package com.anadolusigorta.campaignmanagement.domain.taskmanagement.facade;

import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.CreatePolicyGroup;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.PolicyGroup;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.port.PolicyGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PolicyGroupFacade {

    private final PolicyGroupRepository policyGroupRepository;

    public PolicyGroup save(CreatePolicyGroup policyGroup){
        return policyGroupRepository.save(policyGroup);
    }

    public PageContent<PolicyGroup> getAllPolicyGroups(Pageable pageable) {
        return policyGroupRepository.getAllPolicyGroups(pageable);
    }

    public List<PolicyGroup> getAllPolicyGroups() {
        return policyGroupRepository.getAllPolicyGroups();
    }

    public PolicyGroup getPolicyGroupById(Long id) {
        return policyGroupRepository.getPolicyGroupById(id);
    }

    public void deletePolicyGroupById(Long id) {
        policyGroupRepository.deletePolicyGroupById(id);
    }
}
