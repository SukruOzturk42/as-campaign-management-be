package com.anadolusigorta.campaignmanagement.domain.campaign.facade;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignConjunction;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.CampaignConjunctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignConjunctionFacade {

    private final CampaignConjunctionRepository campaignConjunctionRepository;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CampaignConjunction> getCampaignConjunctions(Long companyId) {
        return campaignConjunctionRepository.getCampaignConjunctionsByCompanyId(companyId);
    }

}
