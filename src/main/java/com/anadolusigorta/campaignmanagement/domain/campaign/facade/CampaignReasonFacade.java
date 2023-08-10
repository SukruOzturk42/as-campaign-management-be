package com.anadolusigorta.campaignmanagement.domain.campaign.facade;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignReason;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.CampaignReasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignReasonFacade {

    private final CampaignReasonRepository campaignReasonRepository;

    public List<CampaignReason> getCampaignReasonsByCampaignId(Long campaignId) {
        return campaignReasonRepository.getCampaignReasonsByCampaignId(campaignId);
    }

    public List<CampaignReason> saveOrUpdateCampaignReason(CampaignReason campaignReason) {
        return campaignReasonRepository.saveOrUpdateCampaignReason(campaignReason);
    }

    public List<CampaignReason> deleteCampaignReasonByCampaignReason(CampaignReason campaignReason) {
        return campaignReasonRepository.deleteCampaignReasonByCampaignReason(campaignReason);
    }

}
