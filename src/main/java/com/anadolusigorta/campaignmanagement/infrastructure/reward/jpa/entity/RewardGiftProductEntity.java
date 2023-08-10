/* odeon_sukruo created on 26.04.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository */

package com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftProduct;
import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftType;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "gift_product")
public class RewardGiftProductEntity extends AbstractEntity {

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description")
	private String description;

	public RewardGiftProduct toModel(){
		return RewardGiftProduct.builder()
				.id(super.getId())
				.name(name)
				.description(description)
				.build();
	}
}
