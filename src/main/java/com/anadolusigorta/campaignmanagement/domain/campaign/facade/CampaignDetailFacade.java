package com.anadolusigorta.campaignmanagement.domain.campaign.facade;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignDetail;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.CampaignDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignDetailFacade {

    private final CampaignDetailRepository campaignDetailRepository;

    public List<CampaignDetail> getCampaignDetailsByCampaignId(Long campaignId) {
        return campaignDetailRepository.getCampaignDetailsByCampaignId(campaignId);
    }

    public List<CampaignDetail> getCampaignDetailsByCampaignIdAndSalesChannel(Long campaignId,String salesChannel) {
        return campaignDetailRepository.getCampaignDetailsByCampaignIdAndSalesChannel(campaignId,salesChannel);
    }

    public List<CampaignDetail> saveOrUpdateCampaignDetail(CampaignDetail campaignGoal) {
        return campaignDetailRepository.saveOrUpdateCampaignDetail(campaignGoal);
    }

    public List<CampaignDetail> deleteCampaignDetailByCampaignDetail(CampaignDetail campaignGoal) {
        return campaignDetailRepository.deleteCampaignDetailByCampaignDetail(campaignGoal);
    }

    public String getCampaignImageFromCM(Long campaignDetailId) {
        return "";
    }

}
