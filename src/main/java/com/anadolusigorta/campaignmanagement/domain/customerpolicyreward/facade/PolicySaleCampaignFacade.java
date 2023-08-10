package com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.facade;

import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.CreatePolicySaleRewardCampaign;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleCustomer;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleGiftCode;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleRewardCampaign;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.port.PolicySaleCampaignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PolicySaleCampaignFacade {

    private final PolicySaleCampaignRepository policySaleCampaignRepository;

    public PolicySaleRewardCampaign createPolicySaleRewardCampaign(CreatePolicySaleRewardCampaign createPolicySaleRewardCampaign){
        return policySaleCampaignRepository.createPolicySaleRewardCampaign(createPolicySaleRewardCampaign);
    }

    public List<PolicySaleRewardCampaign> getPolicySaleRewardCampaigns(){
        return policySaleCampaignRepository.getPolicySaleRewardCampaigns();
    }
    public List<PolicySaleCustomer> getPolicySaleCampaignCustomerList(Long campaignId){
        return policySaleCampaignRepository.getPolicySaleCampaignCustomerList(campaignId);
    }

    public PolicySaleRewardCampaign getPolicySaleCampaignDetail(Long campaignId){
        return policySaleCampaignRepository.getPolicySaleCampaignDetail(campaignId);
    }
    public PolicySaleRewardCampaign distributeCodeToCustomer(Long campaignId){
        return policySaleCampaignRepository.distributeCodeToCustomer(campaignId);
    }

    public List<PolicySaleCustomer> getSuitablePolicySaleRewardCustomer(){
        return policySaleCampaignRepository.findAllSuitablePolicySaleRewardCustomer();
    }

    public void saveSalePolicySaleRewardGift(PolicySaleCustomer policySaleCustomer) {
        policySaleCampaignRepository.saveSalePolicySaleRewardGift(policySaleCustomer);
    }

    public List<PolicySaleCustomer> getPolicySaleRewardCodesByGiftCodeInformationId(Long policySaleRewardCodeInformationId) {
        return policySaleCampaignRepository.getPolicySaleRewardCodesByGiftCodeInformationId(policySaleRewardCodeInformationId);
    }
}
