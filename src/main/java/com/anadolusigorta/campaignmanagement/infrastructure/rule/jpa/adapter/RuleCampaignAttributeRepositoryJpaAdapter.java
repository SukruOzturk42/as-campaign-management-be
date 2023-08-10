package com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.domain.referencetype.model.DataType;
import com.anadolusigorta.campaignmanagement.domain.rule.model.CreateRule;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.CampaignAttributeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.adapter.ReferenceTypeJpaRepositoryAdapter;
import com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.repository.ReferenceTypeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.entity.RuleCampaignAttributeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.entity.RuleEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.repository.CombinedRuleRelationJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.repository.RuleCampaignAttributeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.rule.rest.dto.response.RuleDetailListResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.rule.rest.dto.response.RuleDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.DateFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RuleCampaignAttributeRepositoryJpaAdapter {

	private final RuleCampaignAttributeJpaRepository ruleCampaignAttributeJpaRepository;
	private final CampaignAttributeJpaRepository campaignAttributeJpaRepository;
	private final ReferenceTypeJpaRepositoryAdapter referenceTypeJpaRepositoryAdapter;
	private final CombinedRuleRelationJpaRepository combinedRuleRelationJpaRepository;

	public RuleCampaignAttributeEntity saveRuleCampaignAttribute(CreateRule.RuleAttribute ruleAttribute,Long campaignTypeId,
			RuleEntity ruleEntity) {

		RuleCampaignAttributeEntity ruleCampaignAttributeEntity = new RuleCampaignAttributeEntity();

		ruleCampaignAttributeEntity.setRule(ruleEntity);
		ruleCampaignAttributeEntity
				.setCampaignAttribute(campaignAttributeJpaRepository
						.findByAttributeIdAndAttributeCampaignTypeIdAndCompanyCampaignStructureName(ruleAttribute.getAttributeId(),
								campaignTypeId,Constants.RULE_STATE_NAME));
		ruleCampaignAttributeEntity.setOperator(ruleAttribute.getOperator());
		ruleCampaignAttributeEntity.setValue(ruleAttribute.getValue() != null
				? String.join(Constants.ATTRIBUTE_VALUE_DELIMITER, ruleAttribute.getValue())
				: null);

		return ruleCampaignAttributeJpaRepository.save(ruleCampaignAttributeEntity);
	}

	public RuleDetailListResponse getRuleDetailResponseList(RuleEntity ruleEntity) {
		List<RuleDetailResponse> attributes = new ArrayList<>();
		ruleEntity.getRuleAttributes()
				.forEach(item -> attributes.add(RuleDetailResponse.builder().id(item.getId()).type(Constants.PARAMETER)
						.attributeId(item.getCampaignAttribute().getAttribute().getId())
						.campaignTypeId(ruleEntity.getCampaignType().getId())
						.operator(item.getOperator().toString())
						.value(List.of(item.getValue().split(","))).build()));
		combinedRuleRelationJpaRepository.findAllByParentRuleId(ruleEntity.getId()).forEach(item -> attributes.add(RuleDetailResponse.builder().id(item.getChildRule().getId())
				.type(Constants.RULE)
				.attributeId(item.getChildRule().getId())
				.campaignTypeId(item.getChildRule().getCampaignType().getId())
				.build()));

		return RuleDetailListResponse.builder()
				.conjunctionOperator(ruleEntity.getConjunctionOperator().getValue())
				.ruleName(ruleEntity.getName())
				.ruleDetailResponseList(attributes)
				.build();
	}

}
