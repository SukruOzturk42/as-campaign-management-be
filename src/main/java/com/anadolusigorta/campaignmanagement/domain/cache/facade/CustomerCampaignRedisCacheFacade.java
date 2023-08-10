package com.anadolusigorta.campaignmanagement.domain.cache.facade;

import java.util.List;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.Campaign;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.CustomerCampaignRedisPort;
import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignApprovalStatusEnum;
import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignStatusEnum;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.customercampaign.port.CustomerCampaignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerCampaignRedisCacheFacade {
	private final CustomerCampaignRepository customerCampaignRepository;
	private final CustomerCampaignRedisPort customerCampaignRedisPort;

	public List<Campaign> reloadCustomerCampaignCache() {
		if(customerCampaignRedisPort.cleanAll().equals(Boolean.TRUE)){
			var campaigns=customerCampaignRepository.findAll();
			campaigns.forEach(customerCampaignRedisPort::putCampaign);
			return customerCampaignRedisPort.getAllCampaigns();
		}else{
			throw new CampaignManagementException("redis.cache.reload.operation.fail");
		}

	}

	public void update(Campaign savedCampaign) {
		var campaignId=savedCampaign.getId().toString();
		if (savedCampaign.getCampaignInformation().getCampaignApprovalStatus()
				.getApprovalStatus() == CampaignApprovalStatusEnum.APPROVED_CAMPAIGN
				&& savedCampaign.getCampaignInformation().getCampaignStatus()
				.getStatus() == CampaignStatusEnum.ACTIVE_CAMPAIGN) {
			customerCampaignRedisPort.deleteCampaignById(campaignId);
			customerCampaignRedisPort.putCampaignIfAbsent(savedCampaign);
		} else if (savedCampaign.getCampaignInformation().getCampaignStatus()
				.getStatus() == CampaignStatusEnum.CLOSED_CAMPAIGN
				|| savedCampaign.getCampaignInformation().getCampaignStatus()
				.getStatus() == CampaignStatusEnum.PENDING_CAMPAIGN) {
			customerCampaignRedisPort.deleteCampaignById(campaignId);
		}
	}

}
