/* odeon_sukruo created on 29.04.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity */

package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.attribute.model.AttributeTypeEnum;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignAttribute;
import com.anadolusigorta.campaignmanagement.infrastructure.attribute.jpa.entity.AttributeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.company.jpa.entity.CompanyCampaignStructureEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.operator.jpa.entity.AttributeOperatorEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "campaign_attribute")
public class CampaignAttributeEntity extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name = "company_campaign_structure_id", nullable = false)
	private CompanyCampaignStructureEntity companyCampaignStructure;


	@ManyToOne
	@JoinColumn(name = "attribute_id", nullable = false)
	private AttributeEntity attribute;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private CampaignAttributeEntity parent;

	@OneToMany(mappedBy = "parent",fetch = FetchType.EAGER)
	private Set<CampaignAttributeEntity> children;


	@OneToMany(mappedBy = "attribute", fetch = FetchType.EAGER)
	private Set<AttributeOperatorEntity> attributeOperators = new HashSet<>();



	@Column(name="attribute_order")
	private Long attributeOrder;

	public CampaignAttribute toModel(){
		return CampaignAttribute.builder()
				.parentId(parent!=null?parent.getId():null)
				.id(super.getId())
				.name(attribute.getName())
				.hasChild(children!=null && !children.isEmpty())
				.description(attribute.getDescription())
				.operators(!attributeOperators.isEmpty() ? attributeOperators.stream()
						.map(AttributeOperatorEntity::toModel)
						.collect(Collectors.toList()) : new ArrayList<>())
				.referenceDataList(new ArrayList<>())
				.dataType(attribute.getDataType())
				.order(attributeOrder)
				.attributeType(AttributeTypeEnum.PARAMETER)
				.campaignType(attribute.getCampaignType().getDescription())
				.campaignStructure(companyCampaignStructure.getDescription())
				.build();
	}
}
