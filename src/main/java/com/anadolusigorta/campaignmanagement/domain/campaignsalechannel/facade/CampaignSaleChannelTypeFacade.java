package com.anadolusigorta.campaignmanagement.domain.campaignsalechannel.facade;

import com.anadolusigorta.campaignmanagement.domain.campaignsalechannel.model.CampaignSaleChannelType;
import com.anadolusigorta.campaignmanagement.domain.campaignsalechannel.port.CampaignSaleChannelTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignSaleChannelTypeFacade {

    private final CampaignSaleChannelTypeRepository campaignSaleChannelTypeRepository;

    public List<CampaignSaleChannelType> findAllCampaignSaleChannelTypes() {
        return campaignSaleChannelTypeRepository.findAllCampaignSaleChannelTypes();
    }

}
