package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignType;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.CampaignTypeRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.CampaignTypeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampaignTypeRepositoryJpaAdapter implements CampaignTypeRepository {
    private  final CampaignTypeJpaRepository campaignTypeJpaRepository;


    @Override
    public List<CampaignType> getCampaignTypes() {
        return campaignTypeJpaRepository.findAll().stream()
                .map(CampaignTypeEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<CampaignType> getCampaignTypesByCompanyId(Long companyId) {
        return campaignTypeJpaRepository.findByCompanyId(companyId).stream()
                .map(CampaignTypeEntity::toModel)
                .collect(Collectors.toList());
    }
}
