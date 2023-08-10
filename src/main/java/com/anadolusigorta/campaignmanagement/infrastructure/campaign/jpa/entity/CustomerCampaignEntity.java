/* dks20165 created on 31.08.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity */

package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.Campaign;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "customer_campaign")
public class CustomerCampaignEntity extends AbstractEntity {

	@OneToOne
	@JoinColumn(name = "campaign_id")
	private CampaignEntity campaign;

	@OneToOne
	@JoinColumn(name = "campaign_version_id")
	private CampaignVersionEntity campaignVersion;

	public Campaign toModel(){
		return Campaign.builder()
				.id(campaign.getId())
				.campaignInformation(campaignVersion.getCampaignInformation().toModel())
				.ruleGroups(campaignVersion.getCampaignRuleGroups()
						.stream()
						.map(CampaignRuleGroupEntity::toModel)
						.collect(Collectors.toList()))
				.build();
	}
}
