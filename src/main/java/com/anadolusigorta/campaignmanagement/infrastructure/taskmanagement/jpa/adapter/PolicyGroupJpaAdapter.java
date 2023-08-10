package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.CreatePolicyGroup;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.PolicyGroup;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.port.PolicyGroupRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.ExceptionConstants;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.PolicyGroupEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.PolicyGroupTypesEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.repository.PolicyGroupJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.repository.PolicyGroupTypesJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PolicyGroupJpaAdapter implements PolicyGroupRepository {

    private final PolicyGroupJpaRepository policyGroupJpaRepository;
    private final PolicyGroupTypesJpaRepository policyGroupTypesJpaRepository;

    @Override
    public void deletePolicyGroupById(Long id) {
        policyGroupJpaRepository.deleteById(id);
    }

    @Override
    public PolicyGroup getPolicyGroupById(Long id) {
        return policyGroupJpaRepository.findById(id)
                .orElseThrow(() -> new CampaignManagementException(ExceptionConstants.POLICY_GROUP_NOT_FOUND))
                .toModel();
    }

    @Override
    public PageContent<PolicyGroup> getAllPolicyGroups(Pageable pageable) {
        var policyGroups = policyGroupJpaRepository.findAll(
                PageRequest.of(pageable.getPageNumber()-1,pageable.getPageSize(),pageable.getSort()));
        return PageContent.<PolicyGroup>builder()
                .content(policyGroups.getContent()
                        .stream()
                        .map(PolicyGroupEntity::toModel)
                        .collect(Collectors.toList()))
                .size(pageable.getPageSize())
                .page(pageable.getPageNumber())
                .totalItems(policyGroups.getTotalElements())
                .build();
    }

    @Override
    public List<PolicyGroup> getAllPolicyGroups() {
        var policyGroups = policyGroupJpaRepository.findAll();
        return policyGroups
                .stream()
                .map(PolicyGroupEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public PolicyGroup save(CreatePolicyGroup policyGroup) {
        var policyGroupEntity = new PolicyGroupEntity();
        if(policyGroup.getId() != null){
            policyGroupEntity = policyGroupJpaRepository.findById(policyGroup.getId())
                    .orElseThrow(() -> new CampaignManagementException(ExceptionConstants.POLICY_GROUP_NOT_FOUND));
            policyGroupTypesJpaRepository.deleteAll(policyGroupEntity.getPolicyGroupTypes());
        }
        policyGroupEntity.setName(policyGroup.getName());
        policyGroupEntity.setPriority(policyGroup.getPriority());
        PolicyGroupEntity finalPolicyGroupEntity = policyGroupEntity;
        List<PolicyGroupTypesEntity> list = policyGroup.getPolicyGroupTypes().stream()
                .map(i -> {
                    var policyGroupTypesEntity = new PolicyGroupTypesEntity();
                    policyGroupTypesEntity.setName(i.getName());
                    policyGroupTypesEntity.setPolicyGroup(finalPolicyGroupEntity);
                    return policyGroupTypesEntity;
                })
                .collect(Collectors.toList());

        policyGroupEntity.setPolicyGroupTypes(list);
        policyGroupJpaRepository.save(policyGroupEntity);
        return policyGroupEntity.toModel();
    }
}
