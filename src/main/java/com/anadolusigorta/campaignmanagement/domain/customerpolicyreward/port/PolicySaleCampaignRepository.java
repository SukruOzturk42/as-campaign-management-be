package com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.port;

import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.CreatePolicySaleRewardCampaign;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleCustomer;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleGiftCode;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleRewardCampaign;

import java.util.List;

public interface PolicySaleCampaignRepository {

    PolicySaleRewardCampaign createPolicySaleRewardCampaign(CreatePolicySaleRewardCampaign createPolicySaleRewardCampaign);

    PolicySaleRewardCampaign getPolicySaleCampaignDetail(Long campaignId);

    List<PolicySaleRewardCampaign> getPolicySaleRewardCampaigns();

    List<PolicySaleCustomer> getPolicySaleCampaignCustomerList(Long campaignId);

    PolicySaleRewardCampaign distributeCodeToCustomer(Long campaignId);

    List<PolicySaleCustomer> findAllSuitablePolicySaleRewardCustomer();

    void saveSalePolicySaleRewardGift(PolicySaleCustomer policySaleCustomer);

    List<PolicySaleCustomer> getPolicySaleRewardCodesByGiftCodeInformationId(Long policySaleRewardCodeInformationId);
}
