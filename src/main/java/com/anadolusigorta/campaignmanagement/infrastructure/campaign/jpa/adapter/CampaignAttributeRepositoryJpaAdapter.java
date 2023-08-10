/* odeon_sukruo created on 29.04.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.adapter */

package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.attribute.model.AttributeTypeEnum;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignAttribute;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CreateCampaignAttribute;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.CampaignAttributeRepository;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.infrastructure.attribute.jpa.repository.AttributeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.attribute.jpa.repository.AttributeTypeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignAttributeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.CampaignAttributeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.CampaignRuleJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.ExceptionConstants;
import com.anadolusigorta.campaignmanagement.infrastructure.company.jpa.repository.CompanyCampaignStructureJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.operator.jpa.repository.AttributeOperatorJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.entity.RuleEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.repository.RuleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampaignAttributeRepositoryJpaAdapter implements CampaignAttributeRepository {

	private final RuleJpaRepository ruleJpaRepository;
	private final CampaignAttributeJpaRepository campaignAttributeJpaRepository;
	private final AttributeTypeJpaRepository attributeTypeJpaRepository;
	private final CompanyCampaignStructureJpaRepository companyCampaignStructureJpaRepository;
	private final AttributeJpaRepository attributeJpaRepository;
	private final CampaignRuleJpaRepository campaignRuleJpaRepository;
	private final AttributeOperatorJpaRepository attributeOperatorJpaRepository;

	@Override
	public List<CampaignAttribute> getCampaignAttributesByCampaignTypeIdAndCampaignStructureId(
			Long campaignTypeId, Long structureId,Long attributeTypeId) {
		var attributeType=attributeTypeJpaRepository.findById(attributeTypeId)
				.orElseThrow(()->new CampaignManagementException("attribute.type.not.found"));
		switch(attributeType.getType()) {
			case PARAMETER:
				return campaignAttributeJpaRepository
						.findByAttributeCampaignTypeIdAndCompanyCampaignStructureIdAndAttributeIsMatFalseAndAttributeIsRewardFalseAndAttributeIsActiveTrue(campaignTypeId,structureId)
						.stream()
							.map(CampaignAttributeEntity::toModel)
							.collect(Collectors.toList());
			case RULE:
				return ruleJpaRepository.findAll().stream()
						.map(this::mapCampaignRuleToCampaignAttribute)
						.collect(Collectors.toList());
			default: throw new CampaignManagementException("attribute.type.not.found");
		}
	}

	@Override
	public CampaignAttribute getCampaignAttributeById(Long id) {
		var campaignAttribute=campaignAttributeJpaRepository.findById(id)
				.orElseThrow(()->new CampaignManagementException(ExceptionConstants.CAMPAIN_ATTRIBUTE_NOT_FOUND));
		return campaignAttribute.toModel();
	}

	@Override public List<CampaignAttribute> getChildCampaignAttributesParentById(Long id) {
		var childEntities=campaignAttributeJpaRepository.findByParentId(id);
         return childEntities!=null?childEntities.stream()
				 .map(CampaignAttributeEntity::toModel)
				 .collect(Collectors.toList())
				 :null;

	}

	private CampaignAttribute mapCampaignRuleToCampaignAttribute(RuleEntity ruleEntity) {
		return CampaignAttribute.builder()
				.attributeType(AttributeTypeEnum.RULE)
				.name(ruleEntity.getName())
				.build();
	}

	@Override
	public List<CampaignAttribute> getAllCampaignAttributes() {
		return campaignAttributeJpaRepository
				.findAll()
				.stream()
				.map(CampaignAttributeEntity::toModel)
				.collect(Collectors.toList());
	}

	@Override
	public CampaignAttribute saveCampaignAttribute(CreateCampaignAttribute campaignAttribute){
		if(!campaignAttributeJpaRepository
				.findByAttributeIdAndCompanyCampaignStructureId(campaignAttribute.getAttributeId(), campaignAttribute.getStructureId()).isEmpty()){
			throw new CampaignManagementException("campaign.attribute.already.defined");
		}
		var campaignAttributeEntity = new CampaignAttributeEntity();
		if(campaignAttribute.getId() != null){
			campaignAttributeEntity = campaignAttributeJpaRepository.findById(campaignAttribute.getId())
				.orElseThrow(() -> new CampaignManagementException(ExceptionConstants.ATTRIBUTE_NOT_FOUND));
		}
		else{
			campaignAttributeEntity.setAttributeOrder(campaignAttributeJpaRepository
					.findTopByOrderByIdDesc().getAttributeOrder() + 1);
		}
		campaignAttributeEntity.setCompanyCampaignStructure(companyCampaignStructureJpaRepository
				.findById(campaignAttribute.getStructureId())
				.orElseThrow(() -> new CampaignManagementException("structure.not.found")));
		campaignAttributeEntity.setAttribute(attributeJpaRepository
				.findById(campaignAttribute.getAttributeId())
				.orElseThrow(() -> new CampaignManagementException(ExceptionConstants.ATTRIBUTE_NOT_FOUND)));
		campaignAttributeJpaRepository.save(campaignAttributeEntity);
		return campaignAttributeEntity.toModel();
	}

	@Override
	public CampaignAttribute deleteCampaignAttribute(Long id) {
		var campaignAttributeEntity = campaignAttributeJpaRepository.findById(id)
				.orElseThrow(() -> new CampaignManagementException(ExceptionConstants.ATTRIBUTE_NOT_FOUND));
		var campaignRule = campaignRuleJpaRepository.findAByCampaignAttributeId(id);
		if(!campaignRule.isEmpty()){
			throw new CampaignManagementException("attributes.used.in.campaign.can.not.be.deleted");
		}
		campaignAttributeJpaRepository.delete(campaignAttributeEntity);
		return campaignAttributeEntity.toModel();
	}

	@Override
	public List<CampaignAttribute> getCampaignMatAttributes() {
		return campaignAttributeJpaRepository.findByAttributeIsMatTrue().stream()
				.map(CampaignAttributeEntity::toModel)
				.collect(Collectors.toList());
	}

	@Override
	public List<CampaignAttribute> getCampaignRewardAttributes() {
		var campaignAttributes = campaignAttributeJpaRepository.findByAttributeIsRewardTrue();
		 campaignAttributes.forEach(item ->
				 item.getAttributeOperators().addAll(attributeOperatorJpaRepository.findByAttributeId(item.getAttribute().getId())));
		return campaignAttributes.stream()
				.map(CampaignAttributeEntity::toModel)
				.collect(Collectors.toList());
	}
}
