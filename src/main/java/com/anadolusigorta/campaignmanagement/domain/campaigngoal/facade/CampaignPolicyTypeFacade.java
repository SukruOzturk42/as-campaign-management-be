package com.anadolusigorta.campaignmanagement.domain.campaigngoal.facade;

import com.anadolusigorta.campaignmanagement.domain.campaigngoal.model.CampaignPolicyType;
import com.anadolusigorta.campaignmanagement.domain.campaigngoal.port.CampaignPolicyTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignPolicyTypeFacade {

    private final CampaignPolicyTypeRepository campaignPolicyTypeRepository;

    public List<CampaignPolicyType> findAllCampaignPolicyType() {
        return campaignPolicyTypeRepository.findAllCampaignPolicyType();
    }

}
