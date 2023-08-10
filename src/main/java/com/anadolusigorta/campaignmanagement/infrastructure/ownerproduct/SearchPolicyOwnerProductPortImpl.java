package com.anadolusigorta.campaignmanagement.infrastructure.ownerproduct;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRuleGroup;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.domain.ownerproduct.port.SearchPolicyOwnerProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchPolicyOwnerProductPortImpl implements SearchPolicyOwnerProductPort {
    @Override
    public Map<String,Object> getOwnerProductFromSearchPolicyService(String customerNo, List<String> contactRoleValues, String vinNumber, CampaignRuleGroup ownerProductRuleGroup) {
        var parametersInfo = new HashMap<String,Object>();


        return parametersInfo;
    }

    @Override
    public Map<String, Object> getOwnerProductFromSearchPolicyService(String customerNo) {
        var parametersInfo = new HashMap<String, Object>();

        return parametersInfo;


    }


}
