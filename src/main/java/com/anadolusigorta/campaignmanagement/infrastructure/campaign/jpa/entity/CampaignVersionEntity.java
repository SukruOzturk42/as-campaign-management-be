/* odeon_sukruo created on 4.05.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity */

package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignVersion;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "campaign_version")
public class CampaignVersionEntity extends AbstractEntity {


	@ManyToOne
	@JoinColumn(name = "campaign_id", nullable = false)
	private CampaignEntity campaign;

	@OneToOne
	@JoinColumn(name = "campaign_information_id")
	private CampaignInformationEntity campaignInformation;

	@OneToMany(mappedBy = "campaignVersion",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<CampaignRuleGroupEntity> campaignRuleGroups;


	@Column(name="version")
	private long version;

	@Column(name = "is_active_version")
	private Boolean isActiveVersion;


	public CampaignVersion toModel(){
		return CampaignVersion.builder()
				.id(getId())
				.version(version)
				.isActiveVersion(isActiveVersion)
				.build();
	}

	public CampaignVersion toExtendedModel(){
		return CampaignVersion.builder()
				.id(getId())
				.version(version)
				.campaignInformation(campaignInformation.toModel())
				.campaignRuleGroups(campaignRuleGroups!=null?
						campaignRuleGroups.stream().map(CampaignRuleGroupEntity::toModel).toList():null)
				.isActiveVersion(isActiveVersion)
				.build();
	}




}
