package com.anadolusigorta.campaignmanagement.domain.campaign.facade;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.anadolusigorta.campaignmanagement.domain.campaign.aspect.CampaignEventCreator;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.*;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import org.springframework.stereotype.Service;

import com.anadolusigorta.campaignmanagement.domain.campaign.port.CampaignRepository;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.CampaignRuleGroupRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CampaignFacade {

	private final CampaignRepository campaignRepository;
	private final CampaignRuleGroupRepository campaignRuleGroupRepository;


	@CampaignEventCreator
	public Campaign saveCampaign(CreateCampaign createCampaign) {
	  return campaignRepository.saveCampaign(createCampaign);
	}

	public Campaign getCampaignById(Long campaignId) {
		return Campaign.builder().id(campaignId)
				.campaignInformation(campaignRepository.findCampaignInformationByCampaignId(campaignId))
				.ruleGroups(campaignRuleGroupRepository.finCampaignRuleGroupsByCampaignId(campaignId)).build();
	}

	public Campaign getCampaignByIdAndVersion(Long campaignId, Long version) {

		return Campaign.builder().id(campaignId)
				.campaignInformation(
						campaignRepository.findCampaignInformationByCampaignIdAndVersion(campaignId, version))
				.ruleGroups(
						campaignRuleGroupRepository.finCampaignRuleGroupsByCampaignIdAndVersion(campaignId, version))
				.build();
	}

	public List<CampaignVersion> getCampaignVersions(Long campaignId) {
		return campaignRepository.getCampaignVersions(campaignId).stream()
				.sorted(Comparator.comparing(CampaignVersion::getVersion).reversed()).collect(Collectors.toList());
	}

	public List<CampaignInformation> getAllCampaignInformationByCampaignId(Long campaignId) {
		return campaignRepository.findAllCampaignInformationByCampaignId(campaignId);
	}

	public List<CampaignInformation> getAllCampaignInformationByResendActive() {
		return campaignRepository.findAllCampaignInformationByResendActive();
	}

	public CampaignInformation getCampaignInformationByCampaignId(Long campaignId) {
		return campaignRepository.findCampaignInformationByCampaignId(campaignId);
	}

	public Campaign getByCampaignIdAndCampaignVersionAndRuleGroupId(Long campaignId, Long version, Long ruleGroupId) {
		return campaignRepository.findByCampaignIdAndCampaignVersionAndRuleGroupId(campaignId, version, ruleGroupId);
	}

	public CampaignRuleGroup  getBiggestDiscountPercentageCampaignRuleGroup(List<Long> ruleGroupIds){
       var ruleGroups=ruleGroupIds.stream()
			   .map(campaignRuleGroupRepository::findById)
			   .toList();
	   var ruleGroup= ruleGroups.stream()
				.filter(CampaignRuleGroup::hasDiscountRatioValue)
				.max(Comparator.comparing(CampaignRuleGroup::getDiscountRatioValue))
				.orElseThrow(()->new CampaignManagementException("campaign.not.found"));

	   var campaign=campaignRepository
			   .findByCampaignIdAndCampaignVersionAndRuleGroupId(ruleGroup.getCampaignId(),
					   ruleGroup.getCampaignVersion(), ruleGroup.getRuleGroupId());

	   if(campaign.getCampaignInformation().getCampaignType().getName().equals(Constants.COOPERATION_CAMPAIGN_TYPE)){

		   var optionalCustomerContactCampaign=ruleGroups.stream()
				   .filter(item->item.hasDiscountRatioValue() &&
						   !item.getRuleGroupId().equals(ruleGroup.getRuleGroupId()) &&
						   item.getDiscountRatioValue()>=ruleGroup.getDiscountRatioValue())
				   .max(Comparator.comparing(CampaignRuleGroup::getDiscountRatioValue));

		   if(optionalCustomerContactCampaign.isPresent()){
			   return optionalCustomerContactCampaign.get();
		   }

	   }

	   return ruleGroup;

	}

}
