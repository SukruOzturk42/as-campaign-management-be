/* dks20165 created on 3.11.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.campaign.port */

package com.anadolusigorta.campaignmanagement.domain.campaign.port;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.Campaign;
import java.util.List;

public interface CustomerCampaignRedisPort {

	List<Campaign> getAllCampaigns();

	Boolean cleanAll();

	 Campaign getCampaignById(String campaignId);

	 void deleteCampaignById(String campaignId);

	void putCampaign(Campaign campaign);


	void putCampaignIfAbsent(Campaign campaign);
}
