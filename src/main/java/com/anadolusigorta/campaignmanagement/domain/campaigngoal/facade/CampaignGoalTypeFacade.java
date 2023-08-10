package com.anadolusigorta.campaignmanagement.domain.campaigngoal.facade;

import com.anadolusigorta.campaignmanagement.domain.campaigngoal.model.CampaignGoalType;
import com.anadolusigorta.campaignmanagement.domain.campaigngoal.port.CampaignGoalTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignGoalTypeFacade {

    private final CampaignGoalTypeRepository campaignGoalTypeRepository;

    public List<CampaignGoalType> findAllCampaignGoalTypes() {
        return campaignGoalTypeRepository.findAllCampaignGoalTypes();
    }
}
