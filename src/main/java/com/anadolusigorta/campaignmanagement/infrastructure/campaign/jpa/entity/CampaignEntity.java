/* odeon_sukruo created on 4.05.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity */

package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity;

import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "campaign")
public class CampaignEntity extends AbstractEntity {
	
	@OneToMany(mappedBy = "campaign",cascade = CascadeType.ALL)
	private Set<CampaignVersionEntity> campaignVersions;




}
