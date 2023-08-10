package com.anadolusigorta.campaignmanagement.domain.campaign.port;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignConjunction;

import java.util.List;

public interface CampaignConjunctionRepository {

    List<CampaignConjunction> getCampaignConjunctionsByCompanyId(Long companyId);

}
