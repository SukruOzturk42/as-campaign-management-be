package com.anadolusigorta.campaignmanagement.domain.campaigngoal.port;

import com.anadolusigorta.campaignmanagement.domain.campaigngoal.model.CampaignGoalType;

import java.util.List;

public interface CampaignGoalTypeRepository {

    List<CampaignGoalType> findAllCampaignGoalTypes();

}
