package com.anadolusigorta.campaignmanagement.domain.ownerproduct.port;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRuleGroup;
import com.anadolusigorta.campaignmanagement.domain.ownerproduct.model.OwnerProduct;

import java.util.List;
import java.util.Map;

public interface SearchPolicyOwnerProductPort {

    Map<String,Object> getOwnerProductFromSearchPolicyService(String customerNo, List<String> contactRoleValues, String vinNumber, CampaignRuleGroup ownerProductRuleGroup);

    Map<String,Object> getOwnerProductFromSearchPolicyService(String customerNo);
}
