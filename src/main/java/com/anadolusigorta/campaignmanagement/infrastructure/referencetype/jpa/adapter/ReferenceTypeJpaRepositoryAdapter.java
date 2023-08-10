package com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.attribute.facade.AttributeFacade;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.referencetype.model.AttributeReferenceType;
import com.anadolusigorta.campaignmanagement.domain.referencetype.port.ReferenceTypeRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.attribute.jpa.repository.AttributeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.CampaignAttributeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.ExceptionConstants;
import com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.entity.ReferenceTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.repository.ReferenceTypeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public  class ReferenceTypeJpaRepositoryAdapter implements ReferenceTypeRepository {

	private final ReferenceTypeJpaRepository referenceTypeJpaRepository;
	private final CampaignAttributeJpaRepository campaignAttributeJpaRepository;
	private final AttributeJpaRepository attributeJpaRepository;

	@Override
	public List<AttributeReferenceType> findByCampaignAttributeId(Long campaignAttributeId) {

		var attribute=campaignAttributeJpaRepository.findById(campaignAttributeId)
				.orElseThrow(()->new CampaignManagementException(ExceptionConstants.CAMPAIN_ATTRIBUTE_NOT_FOUND));

		return referenceTypeJpaRepository.findByAttributeId(attribute.getAttribute().getId()).stream()
				.map(ReferenceTypeEntity::toModel)
				.collect(Collectors.toList());
	}

	@Override
	public List<AttributeReferenceType> findByAttributeId(Long attributeId) {
		return referenceTypeJpaRepository.findByAttributeId(attributeId).stream()
				.map(ReferenceTypeEntity::toModel)
				.collect(Collectors.toList());
	}

	@Override
	public List<AttributeReferenceType> findByAttributeNameAndCampaignType(String attributeName,Long campaignTypeId) {
		return referenceTypeJpaRepository.findByAttributeNameAndAttributeCampaignTypeId(attributeName,campaignTypeId)
				.stream()
				.map(ReferenceTypeEntity::toModel)
				.collect(Collectors.toList());
	}

	@Override
	public List<AttributeReferenceType> findByAttributeNameAndCampaignTypeName(String attributeName,String campaignTypeName) {
		return referenceTypeJpaRepository.findByAttributeNameAndAttributeCampaignTypeName(attributeName,campaignTypeName)
				.stream()
				.map(ReferenceTypeEntity::toModel)
				.collect(Collectors.toList());
	}

	@Override
	public AttributeReferenceType createOrUpdateAttributeReferenceType(AttributeReferenceType attributeReferenceType) {
		ReferenceTypeEntity referenceType;
		if (attributeReferenceType.getId() != null) {
			referenceType = referenceTypeJpaRepository
					.findById(attributeReferenceType.getId())
					.orElseThrow();
		} else {
			referenceType = new ReferenceTypeEntity();
		}
		if(attributeReferenceType.getAttributeId() != null){
		referenceType.setAttribute(attributeJpaRepository.findById(attributeReferenceType.getAttributeId())
				.orElseThrow(()-> new CampaignManagementException("attribute.type.not.found")));}
		referenceType.setName(attributeReferenceType.getName());
		referenceType.setDescription(attributeReferenceType.getDescription());
		return referenceTypeJpaRepository.save(referenceType).toModel();
	}

	@Override
	public void deleteReferenceType(Long id){
		referenceTypeJpaRepository.delete(referenceTypeJpaRepository.findById(id)
				.orElseThrow(()-> new CampaignManagementException("reference.type.not.found")));
	}

	public String getReferenceTypeDescriptionByNameAndAttributeId(String name, Long attributeId) {
		if(name.split(",").length == 1) {
			var referenceType = referenceTypeJpaRepository.findByNameAndAttributeId(name, attributeId);
			return referenceType != null ? referenceType.getDescription() : null;
		} else {
			StringBuilder descriptionList = new StringBuilder();
			for (String item : name.split(",")) {
				var referenceType = referenceTypeJpaRepository.findByNameAndAttributeId(item, attributeId);
				if(referenceType != null) {
					descriptionList.append(referenceType.getDescription());
					descriptionList.append("\n");
				}

			}
			return String.valueOf(descriptionList);
		}
	}
}
