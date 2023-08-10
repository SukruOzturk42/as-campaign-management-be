package com.anadolusigorta.campaignmanagement.domain.campaign.facade;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignType;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.CampaignTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignTypeFacade {
    private final CampaignTypeRepository campaignTypeRepository;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CampaignType> getCampaignTypes() { return campaignTypeRepository.getCampaignTypes(); }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CampaignType> getCampaignTypesByCompanyId(Long companyId) {
        return campaignTypeRepository.getCampaignTypesByCompanyId(companyId);
    }


}
