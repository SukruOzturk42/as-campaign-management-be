package com.anadolusigorta.campaignmanagement.domain.campaign.port;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignReason;

import java.util.List;

public interface CampaignReasonRepository {

    List<CampaignReason> saveOrUpdateCampaignReason(CampaignReason campaignReason);

    List<CampaignReason> deleteCampaignReasonByCampaignReason(CampaignReason campaignReason);

    List<CampaignReason> getCampaignReasonsByCampaignId(Long campaignId);

}
