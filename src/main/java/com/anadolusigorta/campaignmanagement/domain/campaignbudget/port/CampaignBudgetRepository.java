package com.anadolusigorta.campaignmanagement.domain.campaignbudget.port;

import com.anadolusigorta.campaignmanagement.domain.campaignbudget.model.CampaignBudget;
import com.anadolusigorta.campaignmanagement.domain.campaignbudget.model.CreateCampaignBudget;

import java.util.List;

public interface CampaignBudgetRepository {

    CampaignBudget saveCampaignBudget(CreateCampaignBudget createCampaignBudget);
    List<CampaignBudget> getAllCampaignBudgets(Long campaignId);
    void  deleteCampaignBudget(Long campaignId);


}
