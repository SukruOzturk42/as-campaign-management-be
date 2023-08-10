/* odeon_sukruo created on 26.04.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository */

package com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardDiscountDetailType;
import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftDeliveryType;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "gift_delivery_type")
public class GiftDeliveryTypeEntity extends AbstractEntity {

	@Column(name = "name", nullable = false, length = 50, unique = true)
	private String name;

	@Column(name = "description", nullable = false, length = 400)
	private String description;

	public RewardGiftDeliveryType toModel(){
		return RewardGiftDeliveryType.builder()
				.id(super.getId())
				.name(name)
				.description(description)
				.build();
	}
}
