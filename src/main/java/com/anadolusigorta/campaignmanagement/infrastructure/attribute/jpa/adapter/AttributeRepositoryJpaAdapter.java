package com.anadolusigorta.campaignmanagement.infrastructure.attribute.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.attribute.model.Attribute;
import com.anadolusigorta.campaignmanagement.domain.attribute.port.AttributeRepository;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.operator.AttributeOperator;
import com.anadolusigorta.campaignmanagement.domain.operator.OperatorEnum;
import com.anadolusigorta.campaignmanagement.domain.referencetype.model.AttributeReferenceType;
import com.anadolusigorta.campaignmanagement.infrastructure.attribute.jpa.entity.AttributeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.attribute.jpa.entity.AttributeTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.attribute.jpa.repository.AttributeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.attribute.jpa.repository.AttributeTypeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.CampaignAttributeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.CampaignTypeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.ExceptionConstants;
import com.anadolusigorta.campaignmanagement.infrastructure.operator.jpa.entity.AttributeOperatorEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.operator.jpa.repository.AttributeOperatorJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.entity.ReferenceTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.repository.ReferenceTypeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttributeRepositoryJpaAdapter implements AttributeRepository {

    private final AttributeJpaRepository attributeJpaRepository;
    private final AttributeOperatorJpaRepository attributeOperatorJpaRepository;
    private final CampaignAttributeJpaRepository campaignAttributeJpaRepository;
	private final AttributeTypeJpaRepository attributeTypeJpaRepository;
	private final CampaignTypeJpaRepository campaignTypeJpaRepository;
	private final ReferenceTypeJpaRepository referenceTypeJpaRepository;

	@Override public Attribute findById(Long id) {
		return attributeJpaRepository.findById(id)
				.orElseThrow(()->new CampaignManagementException(ExceptionConstants.ATTRIBUTE_NOT_FOUND)).toModel();
	}

	@Override
    public List<Attribute> findAllAttributes() {
        return attributeJpaRepository.findAll().stream()
                .map(AttributeEntity::toModel)
                .collect(Collectors.toList());
    }

	@Override
	public List<AttributeOperator> findAttributeOperatorByCampaignAttributeId(Long campaignAttributeId) {
		var campaignAttribute = campaignAttributeJpaRepository.findById(campaignAttributeId)
				.orElseThrow(() -> new CampaignManagementException(ExceptionConstants.CAMPAIN_ATTRIBUTE_NOT_FOUND));
		return findAttributeOperatorByAttributeId(campaignAttribute.getAttribute().getId());
	}

	@Override
	public List<Attribute> findAttributeByCampaignTypeId(Long campaignTypeId,String structureName) {
		return campaignAttributeJpaRepository
				.findByAttributeCampaignTypeIdAndCompanyCampaignStructureName(campaignTypeId,structureName)
				.stream()
				.map(item->item.getAttribute().toModel())
				.collect(Collectors.toList());
	}

	@Override
	public List<Attribute> findAllAttributeByCampaignTypeId(Long campaignTypeId) {
		return  attributeJpaRepository
				.findByCampaignTypeIdAndIsActiveTrue(campaignTypeId)
				.stream()
				.map(AttributeEntity::toModel)
				.collect(Collectors.toList());
	}

	@Override
	public List<AttributeOperator> findAttributeOperatorByAttributeId(Long attributeId) {
		return attributeOperatorJpaRepository.findByAttributeId(attributeId)
				.stream()
				.map(AttributeOperatorEntity::toModel)
				.collect(Collectors.toList());
	}

	@Override
	public AttributeOperator addAttributeOperator(Long attributeId, String operator) {
		var attribute = attributeJpaRepository.findById(attributeId).orElseThrow(()-> new CampaignManagementException(ExceptionConstants.ATTRIBUTE_NOT_FOUND));
		if(attributeOperatorJpaRepository.findAttributeOperatorEntityByNameAndAttribute(operator,attribute).isPresent()){
			throw new CampaignManagementException("operator.already.defined");
		}
		var attributeOperator = new AttributeOperatorEntity();
		attributeOperator.setName(operator);
		attributeOperator.setOperatorEnum(OperatorEnum.valueOf(operator));
		attributeOperator.setAttribute(attribute);
		attributeOperatorJpaRepository.save(attributeOperator);
		return attributeOperator.toModel();
	}

	@Override
	public AttributeOperator updateAttributeOperator(Long attributeId, String operator, Long id){
		var attribute = attributeJpaRepository.findById(attributeId).orElseThrow(()-> new CampaignManagementException(ExceptionConstants.ATTRIBUTE_NOT_FOUND));
		if(attributeOperatorJpaRepository.findAttributeOperatorEntityByNameAndAttribute(operator,attribute).isPresent()){
			throw new CampaignManagementException("operator.already.defined");
		}
		var attributeOperator = attributeOperatorJpaRepository.findById(id)
				.orElseThrow(()-> new CampaignManagementException("operator.not.found"));
		attributeOperator.setName(operator);
		attributeOperator.setOperatorEnum(OperatorEnum.valueOf(operator));
		attributeOperator.setAttribute(attribute);
		attributeOperatorJpaRepository.save(attributeOperator);
		return attributeOperator.toModel();
	}

	@Override
	public AttributeOperator deleteAttributeOperator(Long id){
		var attributeOperator = attributeOperatorJpaRepository.findById(id)
				.orElseThrow(()-> new CampaignManagementException("operator.not.found"));
		attributeOperatorJpaRepository.delete(attributeOperator);
		return attributeOperator.toModel();
	}

	@Override
	public Attribute saveAttribute(Attribute attribute,Long campaignTypeId){
		var attributeEntity = new AttributeEntity();
		if(attribute.getId() != null){
			attributeEntity = attributeJpaRepository.findById(attribute.getId())
					.orElseThrow(()-> new CampaignManagementException(ExceptionConstants.ATTRIBUTE_NOT_FOUND));
		}

		attributeEntity.setName(attribute.getName());
		attributeEntity.setDescription(attribute.getDescription());
		attributeEntity.setIsMat(attribute.getName().toLowerCase(Locale.ROOT).startsWith("mat"));
		attributeEntity.setDataType(attribute.getDataType());
		AttributeTypeEntity attributeType=attributeTypeJpaRepository.findById(1L)
				.orElseThrow(()-> new CampaignManagementException("attribute.type.not.found"));
		CampaignTypeEntity campaignType=campaignTypeJpaRepository.findById(campaignTypeId)
				.orElseThrow(()-> new CampaignManagementException("campaign.type.not.found"));
		attributeEntity.setAttributeType(attributeType);
		attributeEntity.setCampaignType(campaignType);

		return attributeJpaRepository.save(attributeEntity).toModel();
	}


	@Override
	public Attribute deleteAttribute(Long id){
		var attributeEntity = attributeJpaRepository.findById(id)
				.orElseThrow(()-> new CampaignManagementException(ExceptionConstants.ATTRIBUTE_NOT_FOUND));
		if(!campaignAttributeJpaRepository.findByAttributeId(id).isEmpty()){
			throw new CampaignManagementException("attributes.used.in.campaign.can.not.be.deleted");
		}
		attributeOperatorJpaRepository.deleteAll(attributeOperatorJpaRepository
				.findByAttributeId(id));
		referenceTypeJpaRepository.deleteAll(referenceTypeJpaRepository
				.findByAttributeId(id));
		attributeJpaRepository.delete(attributeEntity);
		return attributeEntity.toModel();
	}

	@Override
	public List<AttributeReferenceType> getAttributeReferenceTypeByName(String name) {
		var attributeEntityList = attributeJpaRepository.findByName(name);

		var attributesId = attributeEntityList.stream()
				.map(AttributeEntity::getId)
				.collect(Collectors.toList());

		return referenceTypeJpaRepository.findByAttributeIdIn(attributesId)
				.stream()
				.map(ReferenceTypeEntity::toModel)
				.collect(Collectors.toList());
	}

}
