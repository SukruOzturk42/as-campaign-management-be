package com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.port;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.UsedCodeGroupInformation;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.CreatePolicySaleGiftCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleGiftCode;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleGiftCodeInformation;

import java.util.List;

public interface PolicySaleGiftCodeRepository {

    List<PolicySaleGiftCodeInformation> getAllPolicySaleGiftCodeInformation();

    PolicySaleGiftCodeInformation savePolicySaleGiftCodeInformation(CreatePolicySaleGiftCodeInformation createPolicySaleGiftCodeInformation, String activeUser);

    List<UsedCodeGroupInformation> getCampaignListByUsedPolicySaleGiftCodeInformationId(Long policySaleGiftCodeInformationId);

    List<PolicySaleGiftCode> getAllGiftCodeByGiftCodeInformationId(Long giftCodeInformationId);

}
