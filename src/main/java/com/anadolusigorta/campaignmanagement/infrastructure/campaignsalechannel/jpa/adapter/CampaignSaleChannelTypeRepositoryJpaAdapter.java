package com.anadolusigorta.campaignmanagement.infrastructure.campaignsalechannel.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaignsalechannel.model.CampaignSaleChannelType;
import com.anadolusigorta.campaignmanagement.domain.campaignsalechannel.port.CampaignSaleChannelTypeRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignsalechannel.jpa.entity.CampaignSaleChannelTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignsalechannel.jpa.repository.CampaignSaleChannelTypeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampaignSaleChannelTypeRepositoryJpaAdapter implements CampaignSaleChannelTypeRepository {

    private final CampaignSaleChannelTypeJpaRepository campaignSaleChannelTypeJpaRepository;

    @Override
    public List<CampaignSaleChannelType> findAllCampaignSaleChannelTypes() {
        return campaignSaleChannelTypeJpaRepository.findAll().stream().map(CampaignSaleChannelTypeEntity::toModel).collect(Collectors.toList());
    }

}
