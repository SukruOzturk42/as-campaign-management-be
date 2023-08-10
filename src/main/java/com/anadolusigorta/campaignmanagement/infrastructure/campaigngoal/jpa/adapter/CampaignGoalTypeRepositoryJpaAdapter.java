package com.anadolusigorta.campaignmanagement.infrastructure.campaigngoal.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaigngoal.model.CampaignGoalType;
import com.anadolusigorta.campaignmanagement.domain.campaigngoal.port.CampaignGoalTypeRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigngoal.jpa.entity.CampaignGoalTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigngoal.jpa.repository.CampaignGoalTypeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampaignGoalTypeRepositoryJpaAdapter implements CampaignGoalTypeRepository {

    private final CampaignGoalTypeJpaRepository campaignGoalTypeJpaRepository;

    @Override
    public List<CampaignGoalType> findAllCampaignGoalTypes() {
        return campaignGoalTypeJpaRepository.findAll().stream().map(CampaignGoalTypeEntity::toModel).collect(Collectors.toList());
    }
}
