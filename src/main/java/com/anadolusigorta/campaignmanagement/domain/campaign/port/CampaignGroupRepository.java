package com.anadolusigorta.campaignmanagement.domain.campaign.port;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignGroup;

import java.util.List;

public interface CampaignGroupRepository {
    List<CampaignGroup> getCampaignGroups();

    CampaignGroup createCampaignGroup(CampaignGroup campaignGroup);
}
