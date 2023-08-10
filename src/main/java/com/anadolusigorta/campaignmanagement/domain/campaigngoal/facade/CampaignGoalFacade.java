package com.anadolusigorta.campaignmanagement.domain.campaigngoal.facade;

import com.anadolusigorta.campaignmanagement.domain.campaigngoal.model.CampaignGoal;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.CampaignGoalRepository;
import com.anadolusigorta.campaignmanagement.domain.campaigngoal.model.CreateCampaignGoal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignGoalFacade {

    private final CampaignGoalRepository campaignGoalRepository;

    public List<CampaignGoal> getCampaignGoalsByCampaignId(Long campaignId) {
        return campaignGoalRepository.getCampaignGoalsByCampaignId(campaignId);
    }

    public List<CampaignGoal> saveOrUpdateCampaignGoal(CreateCampaignGoal campaignGoal) {
        return campaignGoalRepository.saveOrUpdateCampaignGoal(campaignGoal);
    }

    public List<CampaignGoal> deleteCampaignGoalByCampaignGoal(CampaignGoal campaignGoal) {
        return campaignGoalRepository.deleteCampaignGoalByCampaignGoal(campaignGoal);
    }

}
