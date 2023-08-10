package com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.facade;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.UsedCodeGroupInformation;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.CreatePolicySaleGiftCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleGiftCode;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleGiftCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.port.PolicySaleGiftCodeRepository;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.port.PolicySaleRewardCompanyInformationRepository;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.port.PolicySaleRewardGiftTicketTypeRepository;
import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardCompanyInformation;
import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftTicketType;
import com.anadolusigorta.campaignmanagement.domain.user.facade.UserSecurityFacade;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PolicySaleGiftCodeFacade {

    private final PolicySaleGiftCodeRepository policySaleGiftCodeRepository;
    private final PolicySaleRewardGiftTicketTypeRepository policySaleRewardGiftTicketTypeRepository;
    private final PolicySaleRewardCompanyInformationRepository policySaleRewardCompanyInformationRepository;

    private final UserSecurityFacade userSecurityFacade;

    public List<PolicySaleGiftCodeInformation> getAllPolicySaleGiftCodeInformation() {
        return policySaleGiftCodeRepository.getAllPolicySaleGiftCodeInformation();
    }

    public PolicySaleGiftCodeInformation savePolicySaleGiftCodeInformation(CreatePolicySaleGiftCodeInformation createPolicySaleGiftCodeInformation) {
        String activeUser = userSecurityFacade.getActiveUser().getFullName();
        return policySaleGiftCodeRepository.savePolicySaleGiftCodeInformation(createPolicySaleGiftCodeInformation,activeUser);
    }

    public List<UsedCodeGroupInformation> getCampaignListByUsedPolicySaleGiftCodeInformationId(Long policySaleGiftCodeInformationId) {
        return policySaleGiftCodeRepository.getCampaignListByUsedPolicySaleGiftCodeInformationId(policySaleGiftCodeInformationId);
    }

    public List<RewardGiftTicketType> findAllRewardGiftTicketType() {
        return policySaleRewardGiftTicketTypeRepository.findAll();
    }

    public RewardGiftTicketType saveRewardGiftTicketType(RewardGiftTicketType rewardGiftTicketType) {
        return policySaleRewardGiftTicketTypeRepository.save(rewardGiftTicketType);
    }

    public List<RewardCompanyInformation> findAllRewardCompanyInformation() {
        return policySaleRewardCompanyInformationRepository.findAll();
    }

    public RewardCompanyInformation saveRewardCompanyInformation(RewardCompanyInformation rewardCompanyInformation) {
        return policySaleRewardCompanyInformationRepository.save(rewardCompanyInformation);
    }

    public List<PolicySaleGiftCode> getPolicySaleCodesByGiftCodeInformationId(Long giftCodeInformationId) {
        return policySaleGiftCodeRepository.getAllGiftCodeByGiftCodeInformationId(giftCodeInformationId);
    }

}
