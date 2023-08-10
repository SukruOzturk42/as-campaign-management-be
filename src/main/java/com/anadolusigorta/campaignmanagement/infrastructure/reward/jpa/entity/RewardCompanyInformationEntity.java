/* odeon_sukruo created on 26.04.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository */

package com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardCompanyInformation;
import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftType;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "reward_company_information")
public class RewardCompanyInformationEntity extends AbstractEntity {

	@Column(name = "name", nullable = false, length = 50, unique = true)
	private String name;

	@Column(name = "description", nullable = false, length = 400)
	private String description;

	public RewardCompanyInformation toModel(){
		return RewardCompanyInformation.builder()
				.id(super.getId())
				.name(name)
				.description(description)
				.build();
	}
}
