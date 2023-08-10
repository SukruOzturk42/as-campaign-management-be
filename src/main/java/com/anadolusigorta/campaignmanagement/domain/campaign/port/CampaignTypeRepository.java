package com.anadolusigorta.campaignmanagement.domain.campaign.port;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignType;

import java.util.List;

public interface CampaignTypeRepository {

    List<CampaignType> getCampaignTypes();

    List<CampaignType> getCampaignTypesByCompanyId(Long companyId);

}
