/* odeon_sukruo created on 26.04.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository */

package com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftDeliveryStartType;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "gift_delivery_start_type")
public class GiftDeliveryStartTypeEntity extends AbstractEntity {

	@Column(name = "name", nullable = false, length = 50, unique = true)
	private String name;

	@Column(name = "description", nullable = false, length = 400)
	private String description;


	public RewardGiftDeliveryStartType toModel(){
		return RewardGiftDeliveryStartType.builder()
				.id(super.getId())
				.name(name)
				.description(description)
				.build();
	}
}
