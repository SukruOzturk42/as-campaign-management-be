package com.anadolusigorta.campaignmanagement.domain.taskmanagement.port;

import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.CreatePolicyGroup;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.PolicyGroup;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PolicyGroupRepository {

    PolicyGroup save(CreatePolicyGroup policyGroup);

    PageContent<PolicyGroup> getAllPolicyGroups(Pageable pageable);

    List<PolicyGroup> getAllPolicyGroups();

    PolicyGroup getPolicyGroupById(Long id);

    void deletePolicyGroupById(Long id);
}
