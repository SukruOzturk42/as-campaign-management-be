package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignConjunction;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.CampaignConjunctionRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignConjunctionEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.CampaignConjunctionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampaignConjunctionRepositoryJpaAdapter implements CampaignConjunctionRepository {

    private final CampaignConjunctionJpaRepository campaignConjunctionJpaRepository;

    @Override
    public List<CampaignConjunction> getCampaignConjunctionsByCompanyId(Long companyId) {
        return campaignConjunctionJpaRepository.findCampaignConjunctionEntitiesByCompanyId(companyId)
                .stream().map(CampaignConjunctionEntity::toModel).collect(Collectors.toList());
    }
}
