package com.anadolusigorta.campaignmanagement.domain.campaign.port;

import com.anadolusigorta.campaignmanagement.domain.campaigngoal.model.CampaignGoal;
import com.anadolusigorta.campaignmanagement.domain.campaigngoal.model.CreateCampaignGoal;

import java.util.List;

public interface CampaignGoalRepository {

    List<CampaignGoal> saveOrUpdateCampaignGoal(CreateCampaignGoal campaignGoal);

    List<CampaignGoal> deleteCampaignGoalByCampaignGoal(CampaignGoal campaignGoal);

    List<CampaignGoal> getCampaignGoalsByCampaignId(Long campaignId);

}
