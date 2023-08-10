package com.anadolusigorta.campaignmanagement.infrastructure.campaigngoal.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaigngoal.model.CampaignPolicyType;
import com.anadolusigorta.campaignmanagement.domain.campaigngoal.port.CampaignPolicyTypeRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigngoal.jpa.entity.CampaignPolicyTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigngoal.jpa.repository.CampaignPolicyTypeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampaignPolicyTypeRepositoryJpaAdapter implements CampaignPolicyTypeRepository {

    private final CampaignPolicyTypeJpaRepository campaignPolicyTypeJpaRepository;

    @Override
    public List<CampaignPolicyType> findAllCampaignPolicyType() {
        return campaignPolicyTypeJpaRepository.findAll().stream()
                .map(CampaignPolicyTypeEntity::toModel)
                .collect(Collectors.toList());
    }
}
