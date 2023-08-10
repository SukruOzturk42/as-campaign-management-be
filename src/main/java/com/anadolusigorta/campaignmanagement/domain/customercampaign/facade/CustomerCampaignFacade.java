/* dks20165 created on 31.08.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.contactcampaign.facade */

package com.anadolusigorta.campaignmanagement.domain.customercampaign.facade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.*;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.CustomerCampaignRedisPort;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.handler.CampaignRuleGroupHandler;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.anadolusigorta.campaignmanagement.domain.customercampaign.port.CustomerCampaignRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerCampaignFacade {

	private final CustomerCampaignRepository customerCampaignRepository;
	private final CustomerCampaignRedisPort customerCampaignRedisPort;

	private final CampaignRuleGroupHandler campaignRuleGroupHandler;

	public List<Campaign> getActiveCampaigns() {

		log.info("Redis Read start : "+ LocalDateTime.now());
		var cachedCampaigns= customerCampaignRedisPort.getAllCampaigns();
		log.info("Redis  Read completed : "+ LocalDateTime.now());

		if(cachedCampaigns.isEmpty()){
			var campaigns=customerCampaignRepository.findAll();
			campaigns.forEach(customerCampaignRedisPort::putCampaign);
			return campaigns.stream()
					.filter(item -> item.getCampaignInformation()
					.isActiveCampaign())
					.collect(Collectors.toList());
 		}
 		return cachedCampaigns.stream()
 				.filter(item -> item.getCampaignInformation()
 						.isActiveCampaign())
 				.collect(Collectors.toList());
	}


	public List<CampaignInformation> getContactCampaignsInformation(CampaignCriteria campaignCriteria) {
		return customerCampaignRepository.findContactCampaignsInformation(campaignCriteria);
	}

	public List<CampaignInformation> getContactCampaignsInformation(String campaignStatusType,Long campaignTypeId) {
		return customerCampaignRepository.findContactCampaignsInformation(campaignStatusType, campaignTypeId);
	}

	public PageContent<CampaignInformation> getContactCampaignsInformationPageable(CampaignCriteria campaignCriteria, Pageable pageable) {

		var campaigns=customerCampaignRepository
				.findContactCampaignsVersionPageable(campaignCriteria, pageable);
		return PageContent.<CampaignInformation>builder()
				.content(campaigns.getContent()
						.stream()
						.map(CampaignVersion::getCampaignInformation)
						.collect(Collectors.toList()))
				.size(pageable.getPageSize())
				.page(pageable.getPageNumber())
				.totalItems(campaigns.getTotalItems())
				.build();
	}

	public Campaign getCampaignByCampaignId(Long campaignId) {
		return customerCampaignRepository.findByCampaignId(campaignId);
	}

	public Campaign getActiveCampaignByCampaignId(Long campaignId) {
		return customerCampaignRepository.findByCampaignIdAndIsActiveTrue(campaignId);
	}


	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<CampaignInformation> getDiscountCodeDefinedParticipantCampaigns() {
		return customerCampaignRepository.getDiscountCodeDefinedParticipantCampaigns();
	}

	public AvailableCampaign getAvailableCampaignByCampaignIdAndRuleGroupId(Long campaignId,Long ruleGroupId){
		return customerCampaignRepository.findAvailableCampaignByCampaignIdAndRuleGroupId(campaignId,ruleGroupId);
	}

}
