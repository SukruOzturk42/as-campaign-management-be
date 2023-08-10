/* odeon_sukruo created on 30.04.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.operator.jpa */

package com.anadolusigorta.campaignmanagement.infrastructure.operator.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignAttribute;
import com.anadolusigorta.campaignmanagement.domain.operator.AttributeOperator;
import com.anadolusigorta.campaignmanagement.domain.operator.OperatorEnum;
import com.anadolusigorta.campaignmanagement.infrastructure.attribute.jpa.entity.AttributeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignAttributeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "attribute_operator")
public class AttributeOperatorEntity extends AbstractEntity {


	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Column(name = "operator", nullable = false, length = 50)
	@Enumerated(EnumType.STRING)
	private OperatorEnum operatorEnum;


	@ManyToOne
	@JoinColumn(name = "attribute_id")
	private AttributeEntity attribute;


	public AttributeOperator toModel(){
		return AttributeOperator.builder()
				.id(super.getId())
				.name(name)
				.operatorEnum(operatorEnum)
				.build();
	}

}
