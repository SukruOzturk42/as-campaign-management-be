/* odeon_sukruo created on 3.05.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.campaign.model */

package com.anadolusigorta.campaignmanagement.domain.campaign.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "Campaign",timeToLive = 3600)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Campaign implements Serializable {

	private Long id;
	private Long version;
	private CampaignInformation campaignInformation;
	private List<CampaignRuleGroup> ruleGroups;


}
