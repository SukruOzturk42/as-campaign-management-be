package com.anadolusigorta.campaignmanagement.infrastructure.campaignstatus.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignStatus;
import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignStatusEnum;
import com.anadolusigorta.campaignmanagement.domain.campaignstatus.port.CampaignStatusRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignstatus.jpa.entity.CampaignStatusEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignstatus.jpa.repository.CampaignStatusJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampaignStatusJpaAdapter implements CampaignStatusRepository {

    private final CampaignStatusJpaRepository campaignStatusJpaRepository;


    @Override
    public List<CampaignStatus> getCampaignStatuses() {
        return campaignStatusJpaRepository.findAll()
                .stream().map(CampaignStatusEntity::toModel).collect(Collectors.toList());
    }
}
