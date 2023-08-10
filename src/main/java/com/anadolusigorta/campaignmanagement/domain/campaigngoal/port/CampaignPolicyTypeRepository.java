package com.anadolusigorta.campaignmanagement.domain.campaigngoal.port;

import com.anadolusigorta.campaignmanagement.domain.campaigngoal.model.CampaignPolicyType;

import java.util.List;

public interface CampaignPolicyTypeRepository {

    List<CampaignPolicyType> findAllCampaignPolicyType();

}
