package com.anadolusigorta.campaignmanagement.domain.campaignbudget.facade;

import com.anadolusigorta.campaignmanagement.domain.campaignbudget.model.CampaignBudget;
import com.anadolusigorta.campaignmanagement.domain.campaignbudget.model.CreateCampaignBudget;
import com.anadolusigorta.campaignmanagement.domain.campaignbudget.port.CampaignBudgetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignBudgetFacade {

    private final CampaignBudgetRepository campaignBudgetRepository;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public CampaignBudget saveCampaignBudget(CreateCampaignBudget createCampaignBudget) {
        return campaignBudgetRepository.saveCampaignBudget(createCampaignBudget);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CampaignBudget> getAllCampaignBudgets(Long campaignId) {
        return campaignBudgetRepository.getAllCampaignBudgets(campaignId);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public void deleteCampaignBudget(Long campaignId) {
         campaignBudgetRepository.deleteCampaignBudget(campaignId);
    }
}
