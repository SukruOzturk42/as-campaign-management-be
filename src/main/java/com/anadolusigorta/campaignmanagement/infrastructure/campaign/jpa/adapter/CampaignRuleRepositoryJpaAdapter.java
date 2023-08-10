/* odeon_sukruo created on 18.05.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.adapter */

package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.attribute.model.AttributeTypeEnum;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRule;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CreateCampaign;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.CampaignRuleRepository;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.*;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.CampaignAttributeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.CampaignAttributeRuleGroupJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.CampaignAttributeRuleJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.CampaignRuleJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.ExceptionConstants;
import com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.repository.RuleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampaignRuleRepositoryJpaAdapter implements CampaignRuleRepository {

	private final CampaignRuleJpaRepository campaignRuleJpaRepository;
	private final CampaignAttributeJpaRepository campaignAttributeJpaRepository;
	private final RuleJpaRepository ruleJpaRepository;
	private final CampaignAttributeRuleGroupJpaRepository campaignAttributeRuleGroupJpaRepository;
	private final CampaignAttributeRuleJpaRepository campaignAttributeRuleJpaRepository;

	public CampaignRuleEntity createCampaignRuleAttribute(CampaignRuleGroupEntity campaignRuleGroupEntity,
			CreateCampaign.CampaignAttribute campaignAttribute){
		var entity=new CampaignRuleEntity();
		entity.setAttributeType(campaignAttribute.getType());
		entity.setCampaignAttribute(campaignAttribute.getType().equals(AttributeTypeEnum.PARAMETER)?
				campaignAttributeJpaRepository.findById(campaignAttribute.getAttributeId())
				.orElseThrow(()->new CampaignManagementException(ExceptionConstants.CAMPAIN_ATTRIBUTE_NOT_FOUND))
				:null);
		entity.setOperator(campaignAttribute.getOperator());
		entity.setCampaignRuleGroup(campaignRuleGroupEntity);
		entity.setRule(campaignAttribute.getType().equals(AttributeTypeEnum.RULE)?ruleJpaRepository
				.findById(campaignAttribute.getAttributeId()).orElseThrow(()->new CampaignManagementException(ExceptionConstants.RULE_NOT_FOUND))
				:null);
		entity.setValue(campaignAttribute.getValue()!=null?
				String.join(Constants.ATTRIBUTE_VALUE_DELIMITER, campaignAttribute.getValue())
				:null);
		entity.setSubProduct(campaignAttribute.getSubProduct()!=null?
				saveAttributeRuleGroup(campaignAttribute.getSubProduct())
				:null);
		return campaignRuleJpaRepository.save(entity);
	}


	public CampaignRuleEntity createCampaignRuleAttribute(RuleGroupRewardGiftEntity ruleGroupRewardGiftEntity,
														  CreateCampaign.CampaignRuleAttribute campaignAttribute){
		var entity=new CampaignRuleEntity();
		entity.setAttributeType(campaignAttribute.getAttributeType());
		entity.setCampaignAttribute(campaignAttribute.getAttributeType().equals(AttributeTypeEnum.PARAMETER)?
				campaignAttributeJpaRepository.findById(campaignAttribute.getParameterId())
						.orElseThrow(()->new CampaignManagementException(ExceptionConstants.CAMPAIN_ATTRIBUTE_NOT_FOUND))
				:null);
		entity.setOperator(campaignAttribute.getOperator());
		entity.setRuleGroupRewardGift(ruleGroupRewardGiftEntity);
		entity.setRule(campaignAttribute.getAttributeType().equals(AttributeTypeEnum.RULE)?ruleJpaRepository
				.findById(campaignAttribute.getParameterId()).orElseThrow(()->new CampaignManagementException(ExceptionConstants.RULE_NOT_FOUND))
				:null);
		entity.setValue(campaignAttribute.getValue()!=null?
				String.join(Constants.ATTRIBUTE_VALUE_DELIMITER, campaignAttribute.getValue())
				:null);
		entity.setSubProduct(campaignAttribute.getSubProduct()!=null?
				saveAttributeRuleGroup(campaignAttribute.getSubProduct())
				:null);
		return campaignRuleJpaRepository.save(entity);
	}

	public CampaignRuleEntity createCampaignRuleAttribute(CampaignRuleGroupOwnerProductEntity campaignRuleGroupEntity,
			CreateCampaign.CampaignAttribute campaignAttribute){
		var entity=new CampaignRuleEntity();
		entity.setAttributeType(campaignAttribute.getType());
		entity.setCampaignAttribute(campaignAttribute.getType().equals(AttributeTypeEnum.PARAMETER)?
				campaignAttributeJpaRepository.findById(campaignAttribute.getAttributeId())
						.orElseThrow(()->new CampaignManagementException(ExceptionConstants.CAMPAIN_ATTRIBUTE_NOT_FOUND))
				:null);
		entity.setOperator(campaignAttribute.getOperator());
		entity.setOwnerProduct(campaignRuleGroupEntity);
		entity.setRule(campaignAttribute.getType().equals(AttributeTypeEnum.RULE)?ruleJpaRepository
				.findById(campaignAttribute.getAttributeId()).orElseThrow(()->new CampaignManagementException(ExceptionConstants.RULE_NOT_FOUND))
				:null);
		entity.setValue(campaignAttribute.getValue()!=null?
				String.join(Constants.ATTRIBUTE_VALUE_DELIMITER, campaignAttribute.getValue())
				:null);
		entity.setSubProduct(campaignAttribute.getSubProduct()!=null?
				saveAttributeRuleGroup(campaignAttribute.getSubProduct())
				:null);
		return campaignRuleJpaRepository.save(entity);
	}

	public CampaignRuleEntity createCampaignRuleAttribute(CampaignRuleGroupRelatedCooperationEntity groupRelatedCooperationEntity,
			CreateCampaign.CampaignAttribute campaignAttribute){
		var entity=new CampaignRuleEntity();
		entity.setAttributeType(campaignAttribute.getType());
		entity.setCampaignAttribute(campaignAttribute.getType().equals(AttributeTypeEnum.PARAMETER)?
				campaignAttributeJpaRepository.findById(campaignAttribute.getAttributeId())
						.orElseThrow(()->new CampaignManagementException(ExceptionConstants.CAMPAIN_ATTRIBUTE_NOT_FOUND))
				:null);
		entity.setOperator(campaignAttribute.getOperator());
		entity.setRelatedCooperation(groupRelatedCooperationEntity);
		entity.setRule(campaignAttribute.getType().equals(AttributeTypeEnum.RULE)?ruleJpaRepository
				.findById(campaignAttribute.getAttributeId()).orElseThrow(()->new CampaignManagementException(ExceptionConstants.RULE_NOT_FOUND))
				:null);
		entity.setValue(campaignAttribute.getValue()!=null?
				String.join(Constants.ATTRIBUTE_VALUE_DELIMITER, campaignAttribute.getValue())
				:null);
		entity.setSubProduct(campaignAttribute.getSubProduct()!=null?
				saveAttributeRuleGroup(campaignAttribute.getSubProduct())
				:null);
		return campaignRuleJpaRepository.save(entity);
	}

	public CampaignRuleEntity createCampaignRuleAttribute(CampaignRuleGroupMatEntity groupMatEntity,
														  CreateCampaign.CampaignAttribute campaignAttribute){
		var entity=new CampaignRuleEntity();
		entity.setAttributeType(campaignAttribute.getType());
		entity.setCampaignAttribute(campaignAttribute.getType().equals(AttributeTypeEnum.PARAMETER)?
				campaignAttributeJpaRepository.findById(campaignAttribute.getAttributeId())
						.orElseThrow(()->new CampaignManagementException(ExceptionConstants.CAMPAIN_ATTRIBUTE_NOT_FOUND))
				:null);
		entity.setOperator(campaignAttribute.getOperator());
		entity.setMatGroup(groupMatEntity);
		entity.setRule(campaignAttribute.getType().equals(AttributeTypeEnum.RULE)?ruleJpaRepository
				.findById(campaignAttribute.getAttributeId()).orElseThrow(()->new CampaignManagementException(ExceptionConstants.RULE_NOT_FOUND))
				:null);
		entity.setValue(campaignAttribute.getValue()!=null?
				String.join(Constants.ATTRIBUTE_VALUE_DELIMITER, campaignAttribute.getValue())
				:null);
		entity.setSubProduct(campaignAttribute.getSubProduct()!=null?
				saveAttributeRuleGroup(campaignAttribute.getSubProduct())
				:null);
		return campaignRuleJpaRepository.save(entity);
	}

	public CampaignRuleEntity createCampaignRuleAttribute(CampaignRuleGroupIsBankEntity groupIsBankEntity,
														  CreateCampaign.CampaignAttribute campaignAttribute){
		var entity=new CampaignRuleEntity();
		entity.setAttributeType(campaignAttribute.getType());
		entity.setCampaignAttribute(campaignAttribute.getType().equals(AttributeTypeEnum.PARAMETER)?
				campaignAttributeJpaRepository.findById(campaignAttribute.getAttributeId())
						.orElseThrow(()->new CampaignManagementException(ExceptionConstants.CAMPAIN_ATTRIBUTE_NOT_FOUND))
				:null);
		entity.setOperator(campaignAttribute.getOperator());
		entity.setIsBank(groupIsBankEntity);
		entity.setRule(campaignAttribute.getType().equals(AttributeTypeEnum.RULE)?ruleJpaRepository
				.findById(campaignAttribute.getAttributeId()).orElseThrow(()->new CampaignManagementException(ExceptionConstants.RULE_NOT_FOUND))
				:null);
		entity.setValue(campaignAttribute.getValue()!=null?
				String.join(Constants.ATTRIBUTE_VALUE_DELIMITER, campaignAttribute.getValue())
				:null);
		entity.setSubProduct(campaignAttribute.getSubProduct()!=null?
				saveAttributeRuleGroup(campaignAttribute.getSubProduct())
				:null);
		return campaignRuleJpaRepository.save(entity);
	}

	public CampaignRuleEntity createCampaignRuleAttribute(CampaignRuleGroupContactProductEntity campaignRuleGroupContactProductEntity,
														  CreateCampaign.CampaignAttribute campaignAttribute){
		var entity=new CampaignRuleEntity();
		entity.setAttributeType(campaignAttribute.getType());
		entity.setCampaignAttribute(campaignAttribute.getType().equals(AttributeTypeEnum.PARAMETER)?
				campaignAttributeJpaRepository.findById(campaignAttribute.getAttributeId())
						.orElseThrow(()->new CampaignManagementException(ExceptionConstants.CAMPAIN_ATTRIBUTE_NOT_FOUND))
				:null);
		entity.setOperator(campaignAttribute.getOperator());
		entity.setContactProduct(campaignRuleGroupContactProductEntity);
		entity.setRule(campaignAttribute.getType().equals(AttributeTypeEnum.RULE)?ruleJpaRepository
				.findById(campaignAttribute.getAttributeId()).orElseThrow(()->new CampaignManagementException(ExceptionConstants.RULE_NOT_FOUND))
				:null);
		entity.setValue(campaignAttribute.getValue()!=null?
				String.join(Constants.ATTRIBUTE_VALUE_DELIMITER, campaignAttribute.getValue())
				:null);
		entity.setSubProduct(campaignAttribute.getSubProduct()!=null?
				saveAttributeRuleGroup(campaignAttribute.getSubProduct())
				:null);
		return campaignRuleJpaRepository.save(entity);
	}


	private CampaignAttributeRuleGroupEntity  saveAttributeRuleGroup(
			CreateCampaign.CampaignAttributeRuleGroup campaignAttributeRuleGroup){
		var campaignAttributeRuleGroupEntity =new CampaignAttributeRuleGroupEntity();
		var savedCampaignAttributeRuleGroupEntity=campaignAttributeRuleGroupJpaRepository.save(campaignAttributeRuleGroupEntity);
		savedCampaignAttributeRuleGroupEntity.setCampaignRules(campaignAttributeRuleGroup.getAttributes().stream()
				.map(item->saveCampaignAttributeRuleEntity(item,savedCampaignAttributeRuleGroupEntity))
				.collect(Collectors.toSet()));
		savedCampaignAttributeRuleGroupEntity.setName("subProduct");
		savedCampaignAttributeRuleGroupEntity.setConjunctionOperator(campaignAttributeRuleGroup.getConjunctionOperator());
		return campaignAttributeRuleGroupJpaRepository.save(savedCampaignAttributeRuleGroupEntity);
	}

	private CampaignAttributeRuleEntity saveCampaignAttributeRuleEntity(CreateCampaign.CampaignAttribute campaignAttribute,
			CampaignAttributeRuleGroupEntity campaignAttributeRuleGroupEntity){
		var entity=new CampaignAttributeRuleEntity();
		entity.setAttributeType(campaignAttribute.getType());
		entity.setCampaignAttribute(campaignAttribute.getType().equals(AttributeTypeEnum.PARAMETER)?
				campaignAttributeJpaRepository.findById(campaignAttribute.getAttributeId())
						.orElseThrow(()->new CampaignManagementException(ExceptionConstants.CAMPAIN_ATTRIBUTE_NOT_FOUND))
				:null);
		entity.setOperator(campaignAttribute.getOperator());
		entity.setValue(campaignAttribute.getValue()!=null?
				String.join(Constants.ATTRIBUTE_VALUE_DELIMITER, campaignAttribute.getValue())
				:null);
		entity.setCampaignAttributeRuleGroup(campaignAttributeRuleGroupEntity);
		return campaignAttributeRuleJpaRepository.save(entity);
	}

	@Override
	public List<CampaignRule> findCampaignRulesByRuleId(Long ruleId) {
		var usedCampaigns = campaignRuleJpaRepository.findAllByRuleId(ruleId);
		var filteredUsedCampaigns = usedCampaigns.stream()
				.filter(i -> i.getCampaignRuleGroup().getCampaignVersion().getIsActiveVersion()).toList();
		return filteredUsedCampaigns.stream()
				.map(CampaignRuleEntity::toCampaignRuleModel)
				.collect(Collectors.toList());
	}
}
