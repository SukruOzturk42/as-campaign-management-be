package com.anadolusigorta.campaignmanagement.domain.campaign.facade;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignGroup;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.CampaignGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignGroupFacade {

    private final CampaignGroupRepository campaignGroupRepository;

    public List<CampaignGroup> getCampaignGroups() {
        return campaignGroupRepository.getCampaignGroups();
    }

    public CampaignGroup createCampaignGroup(CampaignGroup campaignGroup) {
        return campaignGroupRepository.createCampaignGroup(campaignGroup);
    }
}
