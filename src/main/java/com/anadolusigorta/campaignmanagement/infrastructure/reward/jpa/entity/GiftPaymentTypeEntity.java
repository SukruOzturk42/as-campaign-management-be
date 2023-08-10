/* odeon_sukruo created on 26.04.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository */

package com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardDiscountDetailType;
import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftPaymentType;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "gift_payment")
public class GiftPaymentTypeEntity extends AbstractEntity {

	@Column(name = "name", nullable = false, length = 50, unique = true)
	private String name;

	@Column(name = "description", nullable = false, length = 400)
	private String description;

	public RewardGiftPaymentType toModel(){
		return RewardGiftPaymentType.builder()
				.id(super.getId())
				.name(name)
				.description(description)
				.build();
	}
}
